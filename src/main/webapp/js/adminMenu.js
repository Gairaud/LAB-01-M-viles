function loaded(){
    $("#types").on("click", airplaneTypesTable);
    $("#airplanes").on("click", planesTable);
}


function airplaneTypesTable(){
    $("#planes").hide();      
    $("#airplaneTypes").show();
      
}
function planesTable(){
    $("#airplaneTypes").hide();    
    $("#planes").show();
      
}
$(loaded);