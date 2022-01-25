



let firstDraw=true;

let UI;
let animatedEnabled=true;

function dspSetup(){
        UI = new UIClass();

        }//loginSetup


function dspDraw(){

    if (firstDraw==true) {
        UI.rePosition();
        firstDraw=false;
        }

    //закраска фоном
    if (animatedEnabled) {
        fonUpdate();
        image(loginFon, 0, 0, width, height);
        }

    if (animatedEnabled==false) {
        background(0,0,255);
        }


     UI.show();//loginControlShow


     let controlEvent = UI.eventListen(); //прослушивание контроллера

}//loginDraw




function cmdJoinClick(){
        let s1 =  sendAnyHttp("POST","/clientkey","{}");

        alert(s1);
        if (s1.indexOf("error")>-1) {
            alert(s1);
            return;
            }

        let respJSON = JSON.parse(s1);//весь ответ как json объект

        myUser = respJSON;
        document.location.href = '/clientindex.html';
        }//cmdJoinClick

function cmdAdminClick(){

    var adminPassword = prompt("root", 'password');

    let s1 =  sendAnyHttp("POST","/rootkey",adminPassword);

    alert(s1);
    if (s1.indexOf("error")>-1) {
        alert(s1);
        return;
        }


    let respJSON = JSON.parse(s1);//весь ответ как json объект

    myUser = respJSON;
    document.location.href = '/adminindex.html';


    }//cmdAdminClick




