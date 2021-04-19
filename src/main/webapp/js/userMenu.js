function loaded(){
   isAdmin()
   loadMyTrips();
   loadFlights();
   $("#showTrips").on("click", tripsTable);
   $("#showSearch").on("click", searchTripsTables);
   $("#searchTrips").hide();  
}

async function loadMyTrips(){
    let userId = sessionStorage.getItem("userId");
    let user = {
        id : userId
    }
    let requestBody = {
        method: "POST",
        body: JSON.stringify(user),
        headers: {'Content-Type': 'application/json'}
    }
    let response = await fetch("http://localhost:9393/get-tickets", requestBody);
    let tickets = await response.json()
    console.log(tickets);
    listTickets(tickets);
    

}

function listTickets(tickets){
    let list=$("#tList");
    list.html("");
    tickets.forEach((t)=>{ ticketRow(list,t);});
}
function ticketRow(list, t){
    let tr =$("<tr class='d-flex' />");
    tr.html(
        "<td class='col-1'>"+t.reservation.id+"</td>"+
        "<td class='col-2'>"+t.flight.route.id+"</td>"+
        "<td class='col-3'>"+t.flight.departureDate.substring(0,10)+"</td>"+
        "<td class='col-3'>"+t.flight.returnDate.substring(0,10)+"</td>"+
        "<td class='col-1'>"+t.flight.price+"</td>"+
        "<td class='col-1'>"+t.row+"</td>"+
        "<td class='col-1'>"+t.row+"</td>"
    );
    list.append(tr);
}
function isAdmin(){
    if(sessionStorage.getItem("isAdmin") == 1)
        $("#admin").css("visibility", "visible"); 
}

function tripsTable(){
    $("#searchTrips").hide();      
    $("#trips").show();
      
}
function searchTripsTables(){
    $("#trips").hide();    
    $("#searchTrips").show();
      
}


async function loadFlights(){

    let requestBody = {
        method: "POST",
        headers: {'Content-Type': 'application/json'}
    }
    let response = await fetch("http://localhost:9393/get-flights", requestBody);
    let flights = await response.json()
    console.log(flights);
    listFlights(flights);
    

}

function listFlights(flight){
    let list=$("#fList");
    list.html("");
    flight.forEach((t)=>{ ticketRow(list,t);});
}
function flightRow(list, f){
    let tr =$("<tr class='d-flex' />");
    tr.html(
        "<th scope=\"col\" class=\"col-1\">"+f.id+"</th>"+
        "<th scope=\"col\" class=\"col-2\"></th>"+
        "<th scope=\"col\" class=\"col-1\">"+f.route+"</th>"+
        "<th scope=\"col\" class=\"col-2\"></th>"+
        "<th scope=\"col\" class=\"col-1\">"+f.departureDate+"</th>"+
        "<th scope=\"col\" class=\"col-2\"></th>"+
        "<th scope=\"col\" class=\"col-1\">"+f.returnDate+"</th>"+
        "<th scope=\"col\" class=\"col-2\"></th>"+
        "<th scope=\"col\" class=\"col-1\">"+f.price+"</th>"+
        "<th scope=\"col\" class=\"col-2\"></th>"+
        "<th scope=\"col\" class=\"col-1\">"+f.price+"</th>"+
        "<th scope=\"col\" class=\"col-2\"></th>"+
        "<td class=\"col-1\" id='delete'><i style='cursor: pointer;' class='fas fa-plane-departure'></i></td>"
        );
        tr.find("#delete").on("click", () => { deletePlane(p); });
    list.append(tr);
}

$(loaded);