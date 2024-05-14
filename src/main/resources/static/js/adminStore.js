$(function(){

   $(".btn-add").click(function(){
      $(".bg-add-store").css("display", "flex");
      $("body").css("overflow", "hidden");
   });

   $(".close-add-panel").click(function (){
      $(".bg-add-store").css("display", "none");
      $("body").css("overflow", "auto");
   });

   $("#store-add-submit").click(function (){
      $("span.error").remove();
      let errorDiv = $("div.error");
      errorDiv.html("");

      let name = checkDomVal($("#store-add-name"), "Please input store name");
      if(name === ""){
         return;
      }

      let description = checkDomVal($("#store-add-description"), "Please input store description");
      if(description === ""){
         return;
      }

      let email = checkDomVal($("#store-add-email"), "Please input store email");
      if(email === ""){
         return;
      }

      let imageDom = $("#store-add-image");
      let images = imageDom[0].files;
      if(images.length === 0){
         appendErrorInfo(imageDom, errorMessage);
         return;
      }
      let image = images[0];

      let category = $(".store-add-category").val();

      let address = checkDomVal($("#store-add-address"), "Please input store address");
      if(address === ""){
         return;
      }

      let geolocation = checkDomVal($("#store-add-geolocation"), "Please input store geolocation");
      if(geolocation === ""){
         return;
      }

      let request = {
         "name": name,
         "description": description,
         "email": email,
         "categoryId": category,
         "address": address,
         "geolocation": geolocation
      };

      let json = JSON.stringify(request);
      let blob = new Blob([json], {
         type: "application/json"
      });
      let formData = new FormData();
      formData.append("image", image);
      formData.append("request", blob);
      console.log(formData);

      $.ajax({
         method: "POST",
         url: "/admin/store",
         data: formData,
         contentType: false,
         processData: false,
         success: function(result){
            if(result.success){
               showToastAndReload("Store add success.");
            }else{
               alert(result.message);
            }
         }
      });
   });

   $(".icon-view").click(function(){
      $(".bg-view-store").css("display", "flex");
      $("body").css("overflow", "hidden");
      let storeId = $(this).attr("id").substring(5);
      $(".store-view-name").html($("#name-"+storeId).html());
      $(".store-view-description").html($("#description-"+storeId).html());
      $(".store-view-email").html($("#email-"+storeId).html());
      $(".store-view-image").attr("src", $("#image-"+storeId).attr("src"));
      $(".store-view-category").html($(".category-"+storeId).html());
      $(".store-view-address").html($("#address-"+storeId).html());
      $(".store-view-geolocation").html($("#geolocation-"+storeId).html());
   });

   $(".close-view-panel").click(function (){
      $(".bg-view-store").css("display", "none");
      $("body").css("overflow", "auto");
   });

   $(".icon-edit").click(function(){
      $(".bg-edit-store").css("display", "flex");
      $("body").css("overflow", "hidden");
      let storeId = $(this).attr("id").substring(5);
      $("#store-edit-store-id").val(storeId);
      $("#store-edit-name").val($("#name-"+storeId).html());
      $("#store-edit-description").val($("#description-"+storeId).html());
      $("#store-edit-email").val($("#email-"+storeId).html());
      $("#store-edit-category").val($("#category-"+storeId).val());
      $("#store-edit-address").val($("#address-"+storeId).html());
      $("#store-edit-geolocation").val($("#geolocation-"+storeId).html());
   });

   $("#store-edit-submit").click(function (){
      $("span.error").remove();
      let errorDiv = $("div.error");
      errorDiv.html("");

      let storeId = $("#store-edit-store-id").val();
      let name = checkDomVal($("#store-edit-name"), "Please input store name");
      if(name === ""){
         return;
      }

      let description = checkDomVal($("#store-edit-description"), "Please input store description");
      if(description === ""){
         return;
      }

      let email = checkDomVal($("#store-edit-email"), "Please input store email");
      if(email === ""){
         return;
      }

      let imageDom = $("#store-edit-image");
      let images = imageDom[0].files;
      let image = "";
      if(images.length > 0){
         image = images[0];
      }

      let categoryId = $("#store-edit-category").val();

      let address = checkDomVal($("#store-edit-address"), "Please input store address");
      if(address === ""){
         return;
      }

      let geolocation = checkDomVal($("#store-edit-geolocation"), "Please input store geolocation");
      if(geolocation === ""){
         return;
      }

      let request = {
         "name": name,
         "description": description,
         "email": email,
         "categoryId": categoryId,
         "address": address,
         "geolocation": geolocation
      };

      let json = JSON.stringify(request);
      let blob = new Blob([json], {
         type: "application/json"
      });
      let formData = new FormData();
      formData.append("image", image);
      formData.append("request", blob);
      console.log(formData);

      $.ajax({
         method: "PUT",
         url: "/admin/store/"+storeId,
         data: formData,
         contentType: false,
         processData: false,
         success: function(result){
            if(result.success){
               showToastAndReload("Store edit success.");
            }else{
               alert(result.message);
            }
         }
      });
   });

   $(".close-edit-panel").click(function (){
      $(".bg-edit-store").css("display", "none");
      $("body").css("overflow", "auto");
   });

   $(".icon-delete").click(function(){
      let storeId = $(this).attr("id").substring(7);
      if(window.confirm("Confirm delete this store? ")){
         $.ajax({
            method: "DELETE",
            url: "/admin/store/"+storeId,success: function(result){
               if(result.success){
                  showToastAndReload("Store delete success.");
               }else{
                  alert(result.message);
               }
            }
         });
      }
   });

   $(".paginate-select").change(function () {
      let pageIndex = $(this).val();
      let category = $(".select-store-category").val();
      let keyword = $(".keyword").val();
      window.location.href = "/admin/store?category=" + category + "&keyword=" + keyword + "&pageIndex=" + pageIndex + "&pageSize=4";
   });

   $(".btn-search").click(function () {
      let category = $(".select-store-category").val();
      let keyword = $(".keyword").val();
      window.location.href = "/admin/store?category=" + category + "&keyword=" + keyword + "&pageIndex=1&pageSize=4";
   });
});
