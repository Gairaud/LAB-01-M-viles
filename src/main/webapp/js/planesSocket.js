import {listPlanes, comboPlanes} from "./adminMenu.js"


const planeSocket = new WebSocket("ws://localhost:9393/controllerplanes");

planeSocket.onerror = function (event) {
    onError(event)
};
planeSocket.onopen = function (event) {
    onOpen(event)
};
planeSocket.onmessage = function (event) {
    getMessageData(event)
};

function onOpen(event) {
    console.log(event);
    console.log("Abierto")
    getPlanes();
}
 
function onError(event) {
    console.log(event);
}

function getPlanes(){
    var action = {

        "action" : "planes-list"

    }
    planeSocket.send(JSON.stringify(action));
}



function getMessageData(event){
    const eventPlanes = JSON.parse(event.data);
    listPlanes(eventPlanes);
    comboPlanes(eventPlanes);
}

