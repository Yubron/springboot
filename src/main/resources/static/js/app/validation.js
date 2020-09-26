function formValidation() {
    var isValid;
    var forms = document.getElementsByClassName('needs-validation');
    var validation = Array.prototype.filter.call(forms, function(form) {
        if (form.checkValidity() === false) {
            isValid = false;
        }else{
            isValid = true;
        }
    });
    return Boolean(isValid);
}

(function() {
  'use strict';
  window.addEventListener('load', function() {
    // Fetch all the forms we want to apply custom Bootstrap validation styles to
    var forms = document.getElementsByClassName('needs-validation');
    // Loop over them and prevent submission
    var validation = Array.prototype.filter.call(forms, function(form) {
      form.addEventListener('click', function(event) {
        if (form.checkValidity() === false) {
          //event.preventDefault();
          //event.stopPropagation();
        }
        form.classList.add('was-validated');
      }, false);
    });
  }, false);
})();

function effectiveDateValidation() {
    var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth()+1; //January is 0!
    var yyyy = today.getFullYear();
     if(dd<10){
            dd='0'+dd
        }
        if(mm<10){
            mm='0'+mm
        }

    today = yyyy+'-'+mm+'-'+dd;
    document.getElementById("effectiveToDate").setAttribute("min", today);
    document.getElementById("effectiveFromDate").setAttribute("min", today);
}

$('#effectiveFromDate').on('change', function(){
    var effectiveFromDate = $('#effectiveFromDate').val();
    var effectiveToDate = $('#effectiveToDate').val();
    if(effectiveFromDate > effectiveToDate) {
        $('#effectiveToDate').val(effectiveFromDate);
    }
    document.getElementById("effectiveToDate").setAttribute("min", effectiveFromDate);
});

$('#effectiveToDate').on('change', function(){
    var effectiveFromDate = $('#effectiveFromDate').val();
    var effectiveToDate = $('#effectiveToDate').val();
    document.getElementById("effectiveToDate").setAttribute("min", effectiveFromDate);
});


$('#frm-upload').on('change', function(){
    this.classList.add("was-validated");
});


effectiveDateValidation();