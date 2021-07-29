const imgInput = document.getElementById('register-img-input');
const imgBox = document.querySelector('.register-img-box > img');
const blank = document.querySelector('.register-img-box .blank');

const nameInput = document.querySelector('.register-container > .register-name');
const tagInput = document.querySelector('.register-container > .register-tag');

const regButton = document.querySelector('.register-button-box');
const dictFrame = document.querySelector('.dict-frame');


function init(){
    initLoad().then( (res) => {
        res.forEach(elmt => {
            makeProfile(elmt.image, elmt.name, elmt.profile);
        })
    });
}


function initLoad(){
    return fetch("http://172.16.236.12:8000/back/persons", {
        method: 'GET',
        headers: {
            // 'Content-Type': false,
            'Access-Control-Allow-Origin': '*'
        },
        mode: 'cors',
    }).then( (response) => {
        if (response.status >= 200 && response.status < 300) {
            return Promise.resolve(response)
        } else {
            return Promise.reject(new Error(response.statusText))
        }
    })
    .then( (response) => {
        return Promise.resolve(response.json());
    })
}

function getBase64(file) {
    return new Promise( (resolve, reject) => {
        var reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = () => {
            resolve(reader.result);
        }
        reader.onerror = (err) => {
            reject(err);
        }
    })
}

async function imgUpload(){
    if(imgInput.files.length === 0 ) {
        imgBox.style.visibility = 'hidden';
        blank.style.visibility = 'visible';
        imgBox.src = '';
        return;
    }
    const file = imgInput.files[0];
    const imgCode = await getBase64(file);
    imgBox.src = imgCode;
    blank.style.visibility = 'hidden';
    imgBox.style.visibility = 'visible';
    return;
}

function doRegister(){
    // close button
    const file = imgInput.files[0];
    const name = nameInput.value;
    const tag = tagInput.value;
    if(!checkInput(file, name, tag)){
        alert("모든 값을 정확히 입력해주세요.");
        return;
    }
    savePerson(file, name, tag).then( (res) => {
        makeProfile(res.image, res.name, res.profile);
        // open Button
    })
    cleanInput();
}

function checkInput(file, name, tag){
    if(!file) return false;
    if(!name) return false;
    if(!tag) return false;
    return true;
}

function savePerson(file, name, tag){
    const formData = new FormData();
    formData.append("name", name);
    formData.append("image", file);
    formData.append("profile", tag);
    return fetch("http://172.16.236.12:8000/back/person", {
        method: 'POST',
        headers: {
            // 'Content-Type': false,
            'Access-Control-Allow-Origin': '*'
        },
        mode: 'cors',
        body: formData
    }).then( (response) => {
        if (response.status >= 200 && response.status < 300) {
            return Promise.resolve(response)
        } else {
            return Promise.reject(new Error(response.statusText))
        }
    })
    .then( (response) => {
        return Promise.resolve(response.json());
    })
}

function makeProfile(imgCode, name, tag){
    const person = document.createElement('div');
    const personImg = document.createElement('div');
    const personName = document.createElement('div');
    const personTag = document.createElement('div');

    person.classList.add('person');
    personImg.classList.add('person-img');
    personName.classList.add('person-name');
    personTag.classList.add('person-tag');

    personImg.style.backgroundImage = `url(data:image/png;base64,${imgCode})`;
    personName.innerHTML = name;
    personTag.innerHTML = tag;

    person.appendChild(personImg);
    person.appendChild(personTag);
    person.appendChild(personName);

    dictFrame.appendChild(person);

    //clean Input
    cleanInput();

    return;
}

function cleanInput(){
    imgInput.files[0] = null;
    imgBox.style.visibility = 'hidden';
    blank.style.visibility = 'visible';
    imgBox.src = '';
    nameInput.value = '';
    tagInput.value = '';
    return;
}