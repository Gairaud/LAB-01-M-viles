function loaded(){
   isAdmin()
}
function isAdmin(){
    if(sessionStorage.getItem("isAdmin") == 1)
        $("#admin").css("visibility", "visible"); 
}
$(loaded);