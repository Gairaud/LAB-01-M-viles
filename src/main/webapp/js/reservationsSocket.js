import {listReservations} from "./userMenu.js"


const reservationsSocket = new WebSocket("ws://localhost:9393/controllerreservations");

reservationsSocket.onerror = function (event) {
    onError(event)
};
reservationsSocket.onopen = function (event) {
    onOpen(event)
};
reservationsSocket.onmessage = function (event) {
    getMessageData(event)
};

function onOpen(event) {
    console.log(event);
    console.log("Abierto")
    getReservations();
}
 
function onError(event) {
    console.log(event);
}

function getReservations(){
    var action = {

        "action" : "reservation-list"

    }
    reservationsSocket.send(JSON.stringify(action));
}



function getMessageData(event){
    const eventReservations = JSON.parse(event.data);
    listReservations(eventReservations);
    
}

