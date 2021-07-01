
function loaded(){
    $("#planes").hide();
    $("#schedulesTables").hide();
    $("#routesTable").hide();
    $("#types").on("click", airplaneTypesTable);
    $("#airplanes").on("click", planesTable);
    $("#schedules").on("click", scheduleTable);
    $("#logout").on("click", logout);
    $("#routes").on("click", RoutesTable);
    $("#addBtn").on("click", showModal);
    $("#typeAdd").on("click", ()=>{addType();});
    $("#planeAdd").on("click", ()=>{addPlane();});
    $("#routeAdd").on("click", ()=>{addRoute();});
    $("#saveFlight").on("click", ()=>{addFlight();});
    //loadTypes();
    
    //loadPlanes();
    //loadFlights();
    //loadRoutes();
    //loadCities();
    //loadCountries();
    
}

function logout(){
    location.href="index.html";
}
async function loadCities(){

    let requestBody = {
        method: "POST",
        headers: {'Content-Type': 'application/json'}
    }
    let response = await fetch ("http://localhost:9393/get-cities", requestBody);
    let cities = await response.json();
    citiesCombo(cities);

}

//async function loadCities(){}
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
    console.log(types);
    let list=$("#typeList");
    list.html("");
    types.forEach((t)=>{ typeRow(list,t);});

}

function typeRow(list, t){

    let tr =$("<tr class='d-flex' />");
    tr.html(
        "<td class='col-2'>"+t.id+"</td>"+
        "<td class='col-2'>"+t.year+"</td>"+
        "<td class='col-2'>"+t.brand+"</td>"+
        "<td class='col-2'>"+t.model+"</td>"+
        "<td class='col-1'>"+t.rowsNumber+"</td>"+
        "<td class='col-1'>"+t.columnsNumber+"</td>"+
        "<td class='col-1'>"+t.passengersQuantity+"</td>"+
        "<td class=\"col-1\" id='deleteType'><i style='cursor: pointer;' class='fas fa-trash-alt'></i></td>"
    );
    tr.find("#deleteType").on("click", () => { deleteType(t); });
    list.append(tr);


}

function typesCombo(types){

    let list=$("#typesSelect");
    
    types.forEach((t)=>{ 
        let option =$("<option />");
        option.val(t.id);
        option.html(t.id);
        list.append(option);
    });

}

function citiesCombo(cities){
    /*let list_1=$("#originSelect");
    list_1.html("");
    let list_2=$("#destinySelect");
    list_2.html("");
    cities.forEach((c)=>{ typeOption(list_1,c);});
    cities.forEach((c)=>{ typeOption(list_2,c);});*/
}

async function addType(){

    
    let type =  {

        id : $("#typeId").val(),
        year : $("#typeYear").val(),
        model : $("#typeModel").val(),
        brand : $("#typeBrand").val(),
        passengersQuantity : $("#typeQ").val(),
        rowsNumber : $("#typeRows").val(),
        columnsNumber : $("#typeColumns").val(),
       
    }
    let requestBody = {
        method: "POST",
        body: JSON.stringify(type),
        headers: {'Content-Type': 'application/json'}
    }
    console.log(type);
    console.log(requestBody);
  
    await fetch("http://localhost:9393/add-type", requestBody);
    clearTypes();
    loadTypes();
}

async function deleteType(type){

    
    let requestBody = {
        method: "POST",
        body: JSON.stringify(type),
        headers: {'Content-Type': 'application/json'}
    }

    await fetch("http://localhost:9393/delete-type", requestBody);
    loadTypes();
}

async function addPlane(){

    let type_response
    let type =  {

        id : $("#typesSelect").val(),
       
    }
    let requestBody_1 = {
        method: "POST",
        body: JSON.stringify(type),
        headers: {'Content-Type': 'application/json'}
    }
    type_response = await fetch("http://localhost:9393/get-type", requestBody_1);
    let airplaneType = await type_response.json();

    let plane =  {

        id : $("#planeName").val(),
        type: airplaneType
       
    }
    let requestBody_2 = {
        method: "POST",
        body: JSON.stringify(plane),
        headers: {'Content-Type': 'application/json'}
    }
    console.log(plane);
    
    await fetch("http://localhost:9393/add-planes", requestBody_2);
    //clearTypes();
    loadPlanes();



}
/*async function getCity(cityId){

    let response;
    let city = {
        id : cityId
    }
    let requestBody = {
        method: "POST",
        body: JSON.stringify(city),
        headers: {'Content-Type': 'application/json'}
    }
    response  = await fetch ("http://localhost:9393/get-city", requestBody); 
    let finalCity = await response.json();
    console.log(finalCity);
    return finalCity;
}*/
async function addRoute(){

    let destiny = $("#destinySelect").val();
    let origin = $("#originSelect").val();
  

    let route = {
        id: $("#routeName").val(),
        origin: origin,
        destination: destiny,
    }
    let requestBody = {
        method: "POST",
        body: JSON.stringify(route),
        headers: {'Content-Type': 'application/json'}
    }
    await fetch("http://localhost:9393/add-route", requestBody);
    
    loadRoutes();
    console.log(route);

}
async function addFlight(){

    
    let route = {
        id : $("#routesSelect").val()
    }
    let plane = {
        id : $("#planesSelect").val()
    }
    
  

    let flight = {
        route: route,
        departureDate: $("#departureDSelect").val(),
        returnDate: $("#returnDSelect").val(),
        price: $("#fPrice").val(),
        availableSeats: 200,
        plane: plane
    }
    let requestBody = {
        method: "POST",
        body: JSON.stringify(flight),
        headers: {'Content-Type': 'application/json'}
    }
    await fetch("http://localhost:9393/add-flight", requestBody);
    
    loadRoutes();
    console.log(flight);

}
function clearTypes(){


    $("#typeId").val("");
    $("#typeYear").val("");
    $("#typeModel").val("");
    $("#typeBrand").val("");
    $("#typeQ").val("");
    $("#typeRows").val("");
    $("#typeColumns").val("");
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
    comboPlanes(planes);
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
        "<td class=\"col-1\" id='deletePlane'><i style='cursor: pointer;' class='fas fa-trash-alt'></i></td>"
    );
    tr.find("#deletePlane").on("click", () => { deletePlane(p); });
    list.append(tr);

}

async function deletePlane(p){

    
    let requestBody = {
        method: "POST",
        body: JSON.stringify(p),
        headers: {'Content-Type': 'application/json'}
    }

    await fetch("http://localhost:9393/delete-planes", requestBody);
    loadPlanes();
}

async function loadRoutes(){

    let requestBody = {
        method: "POST",
        headers: {'Content-Type': 'application/json'}
    }
    let response = await fetch ("http://localhost:9393/get-routes", requestBody);
    let route = await response.json();
    console.log(route);
    listRoutes(route);
    comboRoutes(route)
}

function comboRoutes(route){

    let list=$("#routesSelect");
    
    route.forEach((t)=>{ 
        let option =$("<option />");
        option.val(t.id);
        option.html(t.id);
        list.append(option);
    });

}

function comboPlanes(plane){

    let list=$("#planesSelect");
    
    plane.forEach((t)=>{ 
        let option =$("<option />");
        option.val(t.id);
        option.html(t.id);
        list.append(option);
    });

}

function listRoutes(route){

    let list=$("#routesList");
    list.html("");
    route.forEach((r)=>{ routeRow(list,r);});
}

function routeRow(list, r){

    let tr =$("<tr class='d-flex' />");
    tr.html(
        "<td class='col-4'>"+r.id+"</td>"+
        "<td class='col-3'>"+r.origin+"</td>"+
        "<td class='col-3'>"+r.destination+"</td>"+
        "<td class=\"col-2\" id='deleteRoute'><i style='cursor: pointer;' class='fas fa-trash-alt'></i></td>"
    );
    tr.find("#delete").on("click", () => { deleteRoute(r); });
    list.append(tr);

}

function deleteRoute(route){

}


async function loadFlights(){

    let requestBody = {
        method: "POST",
        headers: {'Content-Type': 'application/json'}
    }
    let response = await fetch ("http://localhost:9393/get-flights", requestBody);
    let flights = await response.json();
    console.log(flights);
    listFlights(flights);
}

function listFlights(flights){

    let list=$("#flightsList");
    list.html("");
    flights.forEach((f)=>{ flightRow(list,f);});
}

function flightRow(list, f){

    let tr =$("<tr class='d-flex' />");
    tr.html(
        "<td class='col-3'>"+f.route.id+"</td>"+
        "<td class='col-3'>"+f.plane.id+"</td>"+
        "<td class='col-2'>"+f.departureDate+"</td>"+
        "<td class='col-2'>"+f.returnDate+"</td>"+
        "<td class='col-2'>"+"$"+f.price+"</td>"
        
    );
    tr.find("#delete").on("click", () => { deleteSchedule(s); });
    list.append(tr);

}

function showModal(){
    $("#addModal").modal('show');
}
function deleteSchedule(schedule){

}


function airplaneTypesTable(){
    
    $("#planes").hide();      
    $("#schedulesTables").hide();
    $("#routesTable").hide();
    $("#airplaneTypes").show();
    
}
function planesTable(){
    $("#airplaneTypes").hide();    
    $("#planes").show();
    $("#schedulesTables").hide();
    $("#routesTable").hide();
      
}
function scheduleTable(){
    $("#airplaneTypes").hide();   
    $("#planes").hide();   
    $("#schedulesTables").show();
    $("#routesTable").hide();
      
}
function RoutesTable(){
    $("#airplaneTypes").hide();   
    $("#planes").hide();   
    $("#schedulesTables").hide();
    $("#routesTable").show();
      
}
export {listTypes, typesCombo, listPlanes,comboPlanes, listRoutes,comboRoutes,
        listFlights} ;
$(loaded);