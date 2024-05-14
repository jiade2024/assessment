$(function (){
    $(".icon-search-wrapper").click(function(){
        let keyword = $("#keyword").val();
        window.location.href = "/store?keyword="+keyword;
    });
});
