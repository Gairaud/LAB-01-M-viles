import {listTypes, typesCombo} from "./adminMenu.js"
export {getFlights}

const typeSocket = new WebSocket("ws://localhost:9393/controllertypes");

typeSocket.onerror = function (event) {
    onError(event)
};
typeSocket.onopen = function (event) {
    onOpen(event)
};
typeSocket.onmessage = function (event) {
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

        "action" : "types-list"

    }
    typeSocket.send(JSON.stringify(action));
}



function getMessageData(event){
    const eventType = JSON.parse(event.data);
    listTypes(eventType);
    typesCombo(eventType);
}

