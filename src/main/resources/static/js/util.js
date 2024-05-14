function showToast(content, onToastFinish){
    let bgMask = $("<div class='bg-mask bg-mask-show' id='toast-mask'></div>");
    let toastPanel = $("<div class='panel-toast'></div>");
    toastPanel.html(content);
    bgMask.append(toastPanel);
    $("body").append(bgMask);

    setTimeout(function(){
        bgMask.remove();
        if(onToastFinish !== undefined){
            onToastFinish.call();
        }
    }, 1500)
}

function showToastAndReload(content){
    showToast(content, function (){
        window.location.reload();
    })
}

function appendErrorInfo(containerDom, errorInfo){
    containerDom.append($("<span class='error margin-top-extra-small'>" + errorInfo + "</span>"));
}

function checkDomVal(jdom, errorMessage){
    let domVal = jdom.val();
    if(domVal === ""){
        appendErrorInfo(jdom.parent(), errorMessage);
        return "";
    }
    return domVal;
}
