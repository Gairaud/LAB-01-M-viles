function loaded(){
   isAdmin()
   loadMyTrips();
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

$(loaded);