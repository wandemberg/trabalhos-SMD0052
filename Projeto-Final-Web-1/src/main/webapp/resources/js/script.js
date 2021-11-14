/**
 * 
 */
function checkPass()
{
    var pass1 = document.getElementById('psw');
    var message = document.getElementById('error-nwl');
    var goodColor = "#66cc66";
    var badColor = "#ff6666";
	
	//console.log(pass1.value.length);
	//alert(pass1.value.length);
	
    if(pass1.value.length > 5){
        //pass1.style.backgroundColor = goodColor;
        message.style.color = goodColor;
        //message.hidden = "true";
        message.innerHTML = " ";
		return true;
    }
    else{
        //pass1.style.backgroundColor = badColor;
        message.style.color = badColor;
        //message.hidden = "false";
        message.innerHTML = " Digite pelo menos 6 caracteres!";
		return false;
    }
}  


function checkNewPass()
{
    var pass1 = document.getElementById('pass1');
    var pass2 = document.getElementById('pass2');
    var message = document.getElementById('error-nwl');
    var goodColor = "#66cc66";
    var badColor = "#ff6666";

    if(pass1.value == pass2.value){
        pass2.style.backgroundColor = goodColor;
        message.style.color = goodColor;
        message.innerHTML = "ok!";
    }
    else{
        pass2.style.backgroundColor = badColor;
        message.style.color = badColor;
        message.innerHTML = " These passwords don't match";
    }
	
    if(pass1.length > 5){
        pass1.style.backgroundColor = goodColor;
        message.style.color = goodColor;
        message.innerHTML = "character number ok!";
    }
    else{
        pass1.style.backgroundColor = badColor;
        message.style.color = badColor;
        message.innerHTML = " you have to enter at least 6 digit!";
    }
}  