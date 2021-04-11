const inputs = document.querySelectorAll(".input");

function addcl() {
    let parent = this.parentNode.parentNode;
    parent.classList.add("focus");
}

function remcl() {
    let parent = this.parentNode.parentNode;
    if (this.value == "") {
        parent.classList.remove("focus");
    }
}

inputs.forEach(input => {
    input.addEventListener("focus", addcl);
    input.addEventListener("blur", remcl);
});

$("#menu-toggle").click(function (e) {
    e.preventDefault();
    $("#wrapper").toggleClass("toggled");
});

function ShowSelected()
{
    /* Para obtener el valor */
    var cod = document.getElementById("producto").value;
    alert(cod);

    /* Para obtener el texto */
    var combo = document.getElementById("producto");
    var selected = combo.options[combo.selectedIndex].text;
    alert(selected);
}