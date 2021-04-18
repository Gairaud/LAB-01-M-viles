function loaded(){
    show();
    $("#btn").on("click", ()=>{registerService();});
}

async function registerService(){
    let user = {

        id: $("#id").val(),
        userName: $("#username").val(),
        name: $("#name").val(),
        lastName: $("#lastname").val(),
        address: $("#address").val(),
        phone: $("#phone").val(),
        email: $("#email").val(),
        password: $("#pswrd").val(),
        isAdmin: 0,

    }
    console.log(user);
    let requestBody = {
        method: "POST",
        body: JSON.stringify(user),
        headers: {'Content-Type': 'application/json'}
    }

    await fetch("http://localhost:9393/add-user", requestBody);
    //let logSuccess = await response.json();
    //logSuccess?getUserData(requestBody):loginError();

}
async function getUserData(requestBody){

    response = await fetch("http://localhost:9393/get-user", requestBody);
    let user = await response.json();
    sessionStorage.setItem("logedUser", user.userName);
    sessionStorage.setItem("isAdmin", user.isAdmin);
    sessionStorage.setItem("userId", user.id);
    console.log(user);
    location.href="userMenu.html";
}
function loginError(){
    $("#loginErrorModal").modal('show');
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