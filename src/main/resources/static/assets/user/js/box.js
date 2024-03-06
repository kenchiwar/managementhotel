var gallery = document.querySelectorAll("#gallery img");


gallery.forEach((element) => ClickImg(element));

function ClickImg(element) {
    element.addEventListener("click", (e) => {
        var g = document.querySelectorAll("#gallery img");

        var src = "";
        Popup({
            values: g,
            src: src ? src : e.target.currentSrc,
        });
        changeImage();
        activeImage();
        changeButton();
    });
}

function Popup({ values, src }) {
    var galleris = values;
    var categories = [];
    galleris.forEach((e) => categories.push(e.getAttribute("alt")));
    newCategories = [];
    newCategories = categories.filter((e) => {
        return newCategories.includes(e) ? "" : newCategories.push(e);
    });
    var popup = '<div class="popup" >';
    popup +=
        '<a type="button" class="close" onclick="removeP()";><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 384 512"><!--!Font Awesome Free 6.5.1 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2024 Fonticons, Inc.--><path d="M342.6 150.6c12.5-12.5 12.5-32.8 0-45.3s-32.8-12.5-45.3 0L192 210.7 86.6 105.4c-12.5-12.5-32.8-12.5-45.3 0s-12.5 32.8 0 45.3L146.7 256 41.4 361.4c-12.5 12.5-12.5 32.8 0 45.3s32.8 12.5 45.3 0L192 301.3 297.4 406.6c12.5 12.5 32.8 12.5 45.3 0s12.5-32.8 0-45.3L237.3 256 342.6 150.6z"/></svg></a>';
    popup += ' <div class="single-img" > ';
    popup += " <div class='img-wrapper' > ";
    popup += '<img src="' + src + ' " />';
    popup += " </div>";
    popup += " </div>";
    popup += " <div class='categories'>";
    popup +=
        "<button type='button' value='-1' class='active' > All(" +
        galleris.length +
        ") </button> ";
    newCategories.forEach((e, i) => {
        var m = values;
        var q = [];
        m.forEach((el) => {
            if (el.getAttribute("alt") == e ) {
                q.push(el);
            }
        });
        if(e != null){
        popup +=
            "<button type='button' value=" +
            i +
            ">" +
            e +
            "(" +
            q.length +
            ") </button> ";
            }
    });
    popup += " </div>";
    popup += ' <div class="list-img" > ';

    galleris.forEach((e) => {
        popup += " <div class='img-wrapper' > ";
        popup +=
            '<img alt="' +
            e.getAttribute("alt") +
            '" src="' +
            e.currentSrc +
            '"/>';
        popup += "</div>";
    });

    popup += " </div>";
    popup += "</div>";

    var p = document.createElement("div");
    p.classList.add("popup-gallery");
    p.innerHTML = popup;

    document.querySelector(".main-content").appendChild(p);
}

function removeP() {
    document.querySelector(".popup-gallery").remove();
}

function changeImage() {
    var images = document.querySelectorAll(".popup-gallery .list-img img");

    images.forEach((element) => {
        element.addEventListener("click", (e) => {
            var img = document.querySelector(".popup-gallery .single-img img");
            img.src = e.target.currentSrc;
            activeImage();
        });
    });
}

function activeImage() {
    var images = document.querySelectorAll(".popup-gallery .list-img img");
    var img = document.querySelector(".popup-gallery .single-img img");
	
    images.forEach((element) => {
        if (element.currentSrc === img.currentSrc) {
			console.log(element.currentSrc.split("/")[7])
			console.log(img.currentSrc.split("/")[7])
            element.classList.add("active");
        } else {
            element.classList.remove("active");
        }
    });
}

function changeButton() {
    var categories = [];
    var a = document.querySelectorAll(".popup-gallery .categories button");
    var images = document.querySelectorAll(".popup-gallery .list-img img");
    galleris = document.querySelectorAll("#gallery img");
    galleris.forEach((e) => categories.push(e.getAttribute("alt")));
    newCategories = [];
    newCategories = categories.filter((e) => {
        return newCategories.includes(e) ? "" : newCategories.push(e);
    });

    a.forEach((element) => {
        element.addEventListener("click", (el) => {
            i = el.target.value;
            var img = document.querySelector(".popup-gallery .single-img img");
            var pp = [];
           
            images.forEach((e) => {
                if (i >= 0) {
                    if (e.getAttribute("alt") == newCategories[i]) {
                        pp.push(e);
                        e.parentElement.classList.remove("none");
                    } else {
                        e.parentElement.classList.remove("none");
                        e.parentElement.classList.add("none");
                    }
                } else {
                    pp.push(e);
                    e.parentElement.classList.remove("none");
                }
            });
            console.log(pp);
            img.src = pp[0].currentSrc;
            activeImage();
        });
    });
}