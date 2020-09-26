function enableMultiCheck() {
    var selectAll = document.querySelector("#allCheck");
    selectAll.addEventListener('click', function(){
        var objs = document.querySelectorAll(".cartCheck");
        for (var i = 0; i < objs.length; i++) {
            objs[i].checked = selectAll.checked;
        };
    }, false);

    var objs = document.querySelectorAll(".cartCheck");
    for(var i=0; i<objs.length ; i++){
        objs[i].addEventListener('click', function(){
        var selectAll = document.querySelector("#allCheck");
        for (var j = 0; j < objs.length; j++) {
          if (objs[j].checked === false) {
            selectAll.checked = false;
            return;
          };
        };
        selectAll.checked = true;
    }, false);
    }
}
enableMultiCheck();
