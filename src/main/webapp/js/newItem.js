document.addEventListener("DOMContentLoaded", ()=>{

    let inputImg = document.getElementById("image_uploads");
    if(!inputImg) console.error('#inputImg not found - ignored');
    inputImg.style.opacity = 0;
    inputImg.addEventListener("change", updateImageDisplay);
   //var preview = document.querySelector(".preview");

    let addFeatureBtn = document.getElementById("addFeatureBtn");
    if(!addFeatureBtn) console.error('#addFeatureBtn not found - ignored');
    else addFeatureBtn.addEventListener('click', addFeature);

    let deleteFeatureBtn = document.getElementById("deleteFeatureBtn");
    if(!deleteFeatureBtn) console.error('#deleteFeatureBtn not found - ignored');
    else deleteFeatureBtn.addEventListener('click', deleteFeature);

    let newItemBtn = document.getElementById("newItemBtn");
    if(!newItemBtn) console.error('#newItemBtn not found - ignored');
    newItemBtn.addEventListener("click", newItemBtnClick);
});

function deleteFeature(){
    let featureDiv = document.getElementById("featureDiv");
    if(!featureDiv) throw '#featureDiv not found';

    let mainDiv = featureDiv.parentElement;


        mainDiv.lastElementChild.remove();

    if(mainDiv.children.length === 1){
        let deleteFeatureBtn = document.getElementById("deleteFeatureBtn");
        if(!deleteFeatureBtn) throw '#deleteFeatureBtn not found';
        deleteFeatureBtn.classList.add("disabled");
    }
}

let inputCount = 1;
function addFeature(){

    let featureDiv = document.getElementById("featureDiv");
    if(!featureDiv) throw '#featureDiv not found';

    if(featureDiv.parentElement.children.length > 0){
        let deleteFeatureBtn = document.getElementById("deleteFeatureBtn");
        if(!deleteFeatureBtn) throw '#deleteFeatureBtn not found';
        deleteFeatureBtn.classList.remove("disabled");
    }

    let newFeatureDiv = featureDiv.cloneNode();

    let featureNameDiv = document.createElement("div");
    featureNameDiv.classList.add("input-field","col", "s6");
    let inputFeatureName = document.createElement("input");
    inputFeatureName.id = `features_name_${inputCount}`;
    inputFeatureName.name = `features_name_${inputCount}`;
    inputFeatureName.type = "text";


    let labelFeatureName = document.createElement("label");
    labelFeatureName.htmlFor = `features_name_${inputCount}`;
    labelFeatureName.innerText = "Ім'я";
    featureNameDiv.appendChild(inputFeatureName);
    featureNameDiv.appendChild(labelFeatureName);
    //imgBox.appendChild(list);

    let featureDescDiv = featureNameDiv.cloneNode();
    let inputFeatureDesc = document.createElement("input");
    inputFeatureDesc.id = `features_${inputCount}`;
    inputFeatureDesc.name = `features_${inputCount}`;
    inputFeatureDesc.type = "text";

    let labelFeature = document.createElement("label");
    labelFeature.htmlFor = `features_${inputCount}`;
    labelFeature.innerText = "Опис функції";

    featureDescDiv.appendChild(inputFeatureDesc);
    featureDescDiv.appendChild(labelFeature);

    newFeatureDiv.appendChild(featureNameDiv);
    newFeatureDiv.appendChild(featureDescDiv);

    featureDiv.parentElement.appendChild(newFeatureDiv);
    inputCount++;
}


function updateImageDisplay() {

    let inputImg = document.getElementById("image_uploads");
    if(!inputImg) throw '#inputImg not found';
    let img_box = document.querySelector(".img-box");
    if(!img_box) throw '#img-box not found';
    let newInput = inputImg.cloneNode();
    let boardImg = document.getElementById("boardImg");
    if(!boardImg) throw '#inputImg not found';

    if(inputImg.files.length === 0){
        return;
    }

    while (img_box.firstChild) {
        img_box.removeChild(img_box.firstChild);
    }

    let curFiles = inputImg.files;

    if (curFiles.length === 0) {
        let img_elem = document.createElement("img");
        //let arrHrefSplit = window.location.href.split()
        img_elem.src="localhost:8080/Java_Web_SPD111" + "/img/no_image.jpg";
        img_elem.alt="no_image";
        img_box.appendChild(img_elem);
    } else {
        let imgBox = document.querySelector(".img-box");
        if(!imgBox) throw '#imgBox not found';
        let list = document.createElement("div");
        list.classList.add('img-box');


        for (let i = 0; i < curFiles.length; i++) {

            if (validFileType(curFiles[i])) {

                let image = document.createElement("img");
                image.src = window.URL.createObjectURL(curFiles[i]);
                image.alt = "itemImg";
                image.addEventListener('click', selectImg);


                list.appendChild(image);

            } else {
                window.alert("Not a valid file type. Update your selection.")
                // para.textContent =
                //     "File name " +
                //     curFiles[i].name +
                //     ": Not a valid file type. Update your selection.";
                // list.appendChild(para);
            }

            imgBox.appendChild(list);
            let newBoardImg= document.createElement("img");
            newBoardImg.classList.add("item_img");
            newBoardImg.id = "boardImg";
            newBoardImg.src = list.firstElementChild.src;
            newBoardImg.alt = list.firstElementChild.alt;
            boardImg.replaceWith(newBoardImg);
        }


    }
}

function selectImg(e){
    let boardImg = document.getElementById("boardImg");
    if(!boardImg) throw '#inputImg not found';
    console.log(e);
    let newBoardImg= document.createElement("img");
    newBoardImg.classList.add("item_img");
    newBoardImg.src = e.target.currentSrc;
    newBoardImg.id = "boardImg";
    newBoardImg.alt = e.target.alt;
    boardImg.replaceWith(newBoardImg);
}

let fileTypes = ["image/jpeg", "image/pjpeg", "image/png", "image/webp"];

function validFileType(file) {
    for (let i = 0; i < fileTypes.length; i++) {
        if (file.type === fileTypes[i]) {
            return true;
        }
    }

    return false;
}

function newItemBtnClick(e){

    const signupForm = e.target.closest('form') ;
    if( ! signupForm ) {
        throw "Signup form not found" ;
    }
    const DescTextArea = signupForm.querySelector('textarea[name="descrTextArea"]');
    if( ! DescTextArea ) { throw "DescTextArea not found" ; }

    const mainFeatureDiv = document.querySelector(".inputPadNull");
    if( ! mainFeatureDiv ) { throw "mainFeatureDiv not found" ; }

    const imageFiles = signupForm.querySelector('input[name="image_uploads"]');
    if( ! imageFiles ) { throw "imageFiles not found" ; }

    const formData = new FormData() ;
    formData.append( "DescTextArea", DescTextArea.value ) ;

   // let features = [];
    let featureDiv = mainFeatureDiv.children;

    for (let i = 0; i < featureDiv.length; i++ ){

        let features_name_value = featureDiv[i].firstElementChild.firstElementChild.value;
        let features_value = featureDiv[i].firstElementChild.nextElementSibling.firstElementChild.value;
        formData.append(`features_name_${i}`, features_name_value);
        formData.append(`feature_${i}`, features_value);
        // features.push({
        //     features_name: featureDiv[i].firstElementChild.firstElementChild.value,
        //     feature: featureDiv[i].firstElementChild.nextElementSibling.firstElementChild.value
        //
        // });
    }

    // formData.append( "features", JSON.stringify(features)) ;

    console.log(imageFiles.files.length);
    if( imageFiles.files.length > 0 ) {
        for(let i= 0; i < imageFiles.files.length; i++){
            formData.append( `imageFile_${i}`, imageFiles.files[i] ) ;
        }
    }

    fetch( window.location.href, { method: 'POST', body: formData } )
        .then( r => r.json() )
        .then( j => {
            console.log(j);
            // if( j.status == 1 ) {  // реєстрація успішна
            //     alert( 'реєстрація успішна' ) ;
            //     window.location = '/' ;  // переходимо на головну сторінку
            // }
            // else {  // помилка реєстрації (повідомлення - у полі message)
            //     alert( j.data.message ) ;
            // }
        } ) ;
}
// function returnFileSize(number) {
//     if (number < 1024) {
//         return number + "bytes";
//     } else if (number > 1024 && number < 1048576) {
//         return (number / 1024).toFixed(1) + "KB";
//     } else if (number > 1048576) {
//         return (number / 1048576).toFixed(1) + "MB";
//     }
// }