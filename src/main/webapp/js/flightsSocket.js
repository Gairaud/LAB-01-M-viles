import {listFlights} from "./adminMenu.js"


const flightsSocket = new WebSocket("ws://localhost:9393/controllerflights");

flightsSocket.onerror = function (event) {
    onError(event)
};
flightsSocket.onopen = function (event) {
    onOpen(event)
};
flightsSocket.onmessage = function (event) {
    getMessageData(event)
};



function onOpen(event) {
    console.log(event);
    console.log("Abierto")
    getFlights();
}
 
function onError(event) {
    console.log(event);
}

function getFlights(){
    var action = {

        "action" : "flights-list"

    }
    flightsSocket.send(JSON.stringify(action));
}




function getMessageData(event){
    const eventFlights = JSON.parse(event.data);
    listFlights(eventFlights);
}

