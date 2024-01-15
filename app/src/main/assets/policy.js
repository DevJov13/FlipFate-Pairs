var condition = document.getElementById('conditionCheckbox');
var policy = document.getElementById('privacyCheckbox');
var contbtn = document.getElementById('continuebtn');

function checkRadio(){
    contbtn.disabled = !(condition.checked && policy.checked);
}

function checkSubmit() {
    jsBridge.postMessage("userconsent", "Accepted");
}

function checkDismiss() {
    jsBridge.postMessage("userconsent","Dismissed");
}
