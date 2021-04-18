function loaded(){
    $("#planes").hide();  
    $("#types").on("click", airplaneTypesTable);
    $("#airplanes").on("click", planesTable);
    loadTypes();
    loadPlanes();
}

async function loadTypes(){

    let requestBody = {
        method: "POST",
        headers: {'Content-Type': 'application/json'}
    }
    let response = await fetch ("http://localhost:9393/get-types", requestBody);
    let types = await response.json();
    listTypes(types);
    typesCombo(types);

}

function listTypes(types){

    let list=$("#typeList");
    list.html("");
    types.forEach((t)=>{ typeRow(list,t);});

}

function typeRow(list, t){

    let tr =$("<tr class='d-flex' />");
    tr.html(
        "<td class='col-2'>"+t.year+"</td>"+
        "<td class='col-2'>"+t.brand+"</td>"+
        "<td class='col-2'>"+t.model+"</td>"+
        "<td class='col-2'>"+t.rowsNumber+"</td>"+
        "<td class='col-2'>"+t.columnsNumber+"</td>"+
        "<td class='col-1'>"+t.passengersQuantity+"</td>"+
        "<td class=\"col-1\" id='delete'><i style='cursor: pointer;' class='fas fa-trash-alt'></i></td>"
    );
    tr.find("#delete").on("click", () => { deleteType(t); });
    list.append(tr);


}
function deleteType(type){
    console.log(type)
}

function typesCombo(types){

    let list=$("#typesSelect");
    list.html("");
    types.forEach((t)=>{ typeOption(list,t);});

}

function typeOption(list, t){

    var opt = $("<option></option>");
    opt.attr('value', t.id).text(t.id);
    list.append(opt);

    


}
async function loadPlanes(){

    let requestBody = {
        method: "POST",
        headers: {'Content-Type': 'application/json'}
    }
    let response = await fetch ("http://localhost:9393/get-planes", requestBody);
    let planes = await response.json();
    console.log(planes);
    listPlanes(planes);
}

function listPlanes(planes){

    let list=$("#planesList");
    list.html("");
    planes.forEach((p)=>{ planeRow(list,p);});
}

function planeRow(list, p){

    let tr =$("<tr class='d-flex' />");
    tr.html(
        "<td class='col-6'>"+p.id+"</td>"+
        "<td class='col-5'>"+p.type.id+"</td>"+
        "<td class=\"col-1\" id='delete'><i style='cursor: pointer;' class='fas fa-trash-alt'></i></td>"
    );
    tr.find("#delete").on("click", () => { deletePlane(p); });
    list.append(tr);

}

function deletePlane(plane){

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