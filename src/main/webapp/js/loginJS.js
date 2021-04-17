function loaded(){
    show();
    $("#loginForm").submit(loginService);
}
async function loginService(){
    alert("eNTRA");
    let response;
    let user = {
        email: $("#email").val(),
        password: $("#pswrd").val(),
    }
    let requestBody = {
        method: "POST",
        body: JSON.stringify(user),
        headers: {'Content-Type': 'application/json'}
    }

    response = await fetch("http://localhost:9393//login", requestBody);
    console.log("HOLA")
    alert("1");
    let logSuccess = await response.json();
    logSuccess?getUserData():$("#loginErrorModal").modal("show");

}
function getUserData(x){
    alert("Se pudo iniciar");
}
function show(){
    var pswrd = document.getElementById('pswrd');
    var icon = document.querySelector('.fas');
    if (pswrd.type === "password") {
        pswrd.type = "text";
        pswrd.style.marginTop = "20px";
        icon.style.color = "#7f2092";
    }else{
        pswrd.type = "password";
        icon.style.color = "grey";
    }
}
$(loaded);