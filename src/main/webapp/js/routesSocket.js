import {listRoutes, comboRoutes} from "./adminMenu.js"


const routesSocket = new WebSocket("ws://localhost:9393/controllerroutes");

routesSocket.onerror = function (event) {
    onError(event)
};
routesSocket.onopen = function (event) {
    onOpen(event)
};
routesSocket.onmessage = function (event) {
    getMessageData(event)
};

function onOpen(event) {
    console.log(event);
    console.log("Abierto")
    getRoutes();
}
 
function onError(event) {
    console.log(event);
}

function getRoutes(){
    var action = {

        "action" : "routes-list"

    }
    routesSocket.send(JSON.stringify(action));
}



function getMessageData(event){
    const eventRoutes = JSON.parse(event.data);
    listRoutes(eventRoutes);
    comboRoutes(eventRoutes);
}

