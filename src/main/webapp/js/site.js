document.addEventListener( 'DOMContentLoaded', () => {
    // шукаємо кнопку реєстрації, якщо знаходимо - додаємо обробник
    const signupButton = document.getElementById("signup-button");
    if(signupButton) { signupButton.onclick = signupButtonClick; }
    // шукаємо кнопку автентифікації, якщо знаходимо - додаємо обробник
    const authButton = document.getElementById("auth-button");
    if(authButton) { authButton.onclick = authButtonClick; }
    // налаштування модальних вікон
    let elems = document.querySelectorAll('.modal');
    M.Modal.init(elems, {
        "opacity": 	    	0.5, 	// Opacity of the modal overlay.
        "inDuration": 		250, 	// Transition in duration in milliseconds.
        "outDuration": 		250, 	// Transition out duration in milliseconds.
        "onOpenStart": 		null,	// Callback function called before modal is opened.
        "onOpenEnd": 		null,	// Callback function called after modal is opened.
        "onCloseStart":		null,	// Callback function called before modal is closed.
        "onCloseEnd": 		null,	// Callback function called after modal is closed.
        "preventScrolling": true,	// Prevent page from scrolling while modal is open.
        "dismissible": 		true,	// Allow modal to be dismissed by keyboard or overlay click.
        "startingTop": 		'4%',	// Starting top offset
        "endingTop": 		'10%'	// Ending top offset
    });

    checkAuth();


});

function serveCartButtons(){
    const userId = document.querySelector('[data-user-id]').getAttribute('data-user-id');
    for(let btn of document.querySelectorAll('[data-product]')){
        btn.onclick = () =>{

            let productId = btn.getAttribute('data-product')
            fetch(`/${getContext()}/shop-api?user-id=${userId}&product-id=${productId}`, {
                method: 'PUT'
            }).then(r=>r.json()).then(console.log);
        }
    }
}

function getContext(){
    return window.location.pathname.split('/')[1];
}
function authButtonClick() {
    const emailInput = document.querySelector('input[name="auth-email"]');
    if( ! emailInput ) { throw "'auth-email' not found" ; }
    const passwordInput = document.querySelector('input[name="auth-password"]');
    if( ! passwordInput ) { throw "'auth-password' not found" ; }

    //console.log( emailInput.value, passwordInput.value ) ;
    fetch(`/${getContext()}/auth?email=${emailInput.value}&password=${passwordInput.value}`, {
        method: 'GET'
    })
        .then( r => r.json() )
        .then( j => {
            if(j.data == null || typeof j.data.token == "undefined" ){
                document.getElementById('modal-auth-message').innerText = "Access denied";
            }
            else{
                localStorage.setItem("auth-token", j.data.token);
                window.location.reload();
            }
        } ) ;
}
function checkAuth(){
    const authToken = localStorage.getItem("auth-token");
    if( authToken ){
        fetch(`/${getContext()}/auth?token=${authToken}`, {
            method: 'POST'
        })
            .then( r => r.json() )
            .then( j => {
                if(j.meta.status == 'success'){
                    document.querySelector('[data-auth = "avatar"]')
                        .innerHTML = `<img data-user-id="${j.data.id}" class="nav-avatar" src="/${getContext()}/img/avatar/${j.data.avatar}"/>`
                    const product = document.querySelector('[data-auth="product"]');
                    if(product){
                        fetch(`/${getContext()}/product.jsp`)
                            .then(r=> r.text())
                            .then( t => {
                                product.innerHTML = t;
                                document.getElementById("add-product-button")
                                    .addEventListener('click', addProductClick);
                            });
                    }
                    serveCartButtons();
                }
            } ) ;
    }
}
function addProductClick(e){
    const form = e.target.closest('form');
    const name = form.querySelector("#product-name").value.trim();
    const price = Number(form.querySelector("#product-price").value);
    const description = form.querySelector("#product-description").value.trim();
    const fileInput = form.querySelector("#product-img");

    const formData = new FormData();
    formData.append("name", name);
    formData.append("price", price);
    formData.append("description", description);
    formData.append("image", fileInput.files[0]);
    formData.append("token", localStorage.getItem("auth-token"));

    const authToken = localStorage.getItem("auth-token");
try {
    fetch(`/${getContext()}/shop-api?token=${authToken}`, {
        method: 'POST',
        body: formData
    })
        .then(r => r.json())
        .then(j => {
            console.log(j);
            console.log(j.meta);
            if(j.meta.messageTarget === "403"){
                form.parentElement.innerHTML = `<h1>${j.meta.message}</h1>`;
            }
            if (j.meta.status === "error") {
                ErrorMessageHandler(j.meta.messageTarget, j.meta.message, form);
            }
        });
}catch(error){
    console.log(error);
}
}

function ErrorMessageHandler( messageTarget, message, form ){

    const nameInput = form.querySelector("#product-name");
    const priceInput = form.querySelector("#product-price");
    const descrInput = form.querySelector("#product-description");
    const fileInput = document.getElementById("product-img-path");


    switch(messageTarget){
        case "name":
            InputElemInvalid(nameInput, message);
            InputElemValid(priceInput);
            InputElemValid(descrInput);
            InputElemValid(fileInput);
            break;
        case "description":
            InputElemInvalid(descrInput, message);
            InputElemValid(priceInput);
            InputElemValid(nameInput);
            InputElemValid(fileInput);
            break;
        case "price":
            InputElemInvalid(priceInput, message);
            InputElemValid(descrInput);
            InputElemValid(nameInput);
            InputElemValid(fileInput);
            break;
        case "file":
            fileInput.classList.remove("validate");
            fileInput.classList.add("invalid");
            fileInput.nextElementSibling.dataset.error = message;
            InputElemValid(descrInput);
            InputElemValid(nameInput);
            InputElemValid(priceInput);
            break;
    }

}

function InputElemInvalid(InputElem, message){
    InputElem.classList.remove("validate");
    InputElem.classList.add("invalid");
    InputElem.nextElementSibling.nextElementSibling.dataset.error = message;
    console.dir(InputElem.nextElementSibling.nextElementSibling);
}

function InputElemValid(InputElem){
    InputElem.classList.add("validate");
    InputElem.classList.remove("invalid");

}


function signupButtonClick(e) {
    // шукаємо форму - батьківській елемент кнопки (e.target)
    const signupForm = e.target.closest('form') ;
    if( ! signupForm ) {
        throw "Signup form not found" ;
    }
    // всередині форми signupForm знаходимо елементи
    const nameInput = signupForm.querySelector('input[name="user-name"]');
    if( ! nameInput ) { throw "nameInput not found" ; }
    const emailInput = signupForm.querySelector('input[name="user-email"]');
    if( ! emailInput ) { throw "emailInput not found" ; }
    const passwordInput = signupForm.querySelector('input[name="user-password"]');
    if( ! passwordInput ) { throw "passwordInput not found" ; }
    const repeatInput = signupForm.querySelector('input[name="user-repeat"]');
    if( ! repeatInput ) { throw "repeatInput not found" ; }
    const avatarInput = signupForm.querySelector('input[name="user-avatar"]');
    if( ! avatarInput ) { throw "avatarInput not found" ; }

    /// Валідація даних
    let isFormValid = true ;

    if( nameInput.value === "" ) {
        nameInput.classList.remove("valid");
        nameInput.classList.add("invalid");
        isFormValid = false ;
    }
    else {
        nameInput.classList.remove("invalid");
        nameInput.classList.add("valid");
    }

    if( ! isFormValid ) return ;
    /// кінець валідації

    // формуємо дані для передачі на бекенд
    const formData = new FormData() ;
    formData.append( "user-name", nameInput.value ) ;
    formData.append( "user-email", emailInput.value ) ;
    formData.append( "user-password", passwordInput.value ) ;
    if( avatarInput.files.length > 0 ) {
        formData.append( "user-avatar", avatarInput.files[0] ) ;
    }

    // передаємо - формуємо запит
    fetch( window.location.href, { method: 'POST', body: formData } )
        .then( r => r.json() )
        .then( j => {
            if( j.meta.status === "success" ) {  // реєстрація успішна
                alert( 'Реєстрація успішна' ) ;
                window.location = '/' + window.location.pathname.split('/')[1] ;  // переходимо на головну сторінку
            }
            else {  // помилка реєстрації (повідомлення - у полі message)
                signupAlert(signupForm, j.meta.message ) ;
                passwordInput.value = "";
                repeatInput.value = "";
            }
        } ) ;
}

function signupAlert(signupForm, message){

    let errorDiv = document.querySelector('.signAlertDiv');
    if(errorDiv) { console.log(errorDiv); return; }

    let mainDiv = signupForm.parentElement;
    let alertDiv = document.createElement("div");
    alertDiv.classList.add("row","signAlertDiv", "red", "lighten-4");
    let alertSpan = document.createElement("span");
    alertSpan.innerText = message;
    alertDiv.append(alertSpan);
    mainDiv.prepend(alertDiv);

}
