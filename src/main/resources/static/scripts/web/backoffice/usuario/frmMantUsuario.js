var nombre=document.getElementById("txtnombre");
var correo=document.getElementById("txtcorreo");

function validarInputs(){
    //alert("Ingresar correo");
    //if($(this).attr("data-nomusuario") === "" || $(this).attr("data-nomusuario") === null){
    if(nombre.value === '' || nombre.value === null){
        alert("Ingresar nombre");
        return false;
    }
     if($(this).attr("data-usuariocorreo") === '' || $(this).attr("data-usuariocorreo") === null){
        alert("Ingresar correo");
        return false;
    }

    return true;
}

$(document).on("click", "#btnagregar", function(){
    $("#hddiduser").val("0");
    $("#txtnomusuario").val("");
    $("#txtnombre").val("");
    $("#txtcorreo").val("");
    $("#txtpassword").val("");
    $("#chkactivo").prop("checked", true);
    $("#swactivo").hide();
    $("#swestado").hide();
    $("#cboestado").val("A");
    $("#modalNuevo").modal("show");
});

$(document).on("click", ".btnactualizar", function(){
    $("#hddiduser").val($(this).attr("data-usuarioid"));
    $("#txtnomusuario").val($(this).attr("data-nomusuario"));
    $("#txtnombre").val($(this).attr("data-usuarionombre"));
    $("#txtcorreo").val($(this).attr("data-usuariocorreo"));
    $("#txtpassword").val($(this).attr("data-usuariopassword"));
    $("#swactivo").show();
    $("#swestado").show();
    if($(this).attr("data-usuarioactivo") === "true")
            $("#chkactivo").prop("checked", true);
        else
            $("#chkactivo").prop("checked", false);
    //$("#chkactivo").val($(this).attr("data-usuarioactivo"));
    $("#cboestado").val($(this).attr("data-usuarioestado"));
    $("#modalNuevo").modal("show");
});

$(document).on("click", "#btnguardar", function(){
    //validarInputs()
    $.ajax({
        type: "POST",
        url: "/backoffice/usuario/guardar",
        contentType: "application/json",
        data: JSON.stringify({
            id: $("#hddiduser").val(),
            nombre: $("#txtnombre").val(),
            correo: $("#txtcorreo").val(),
            estado: $("#cboestado").val(),
            password: $("#txtpassword").val(),
            activo: $("#chkactivo").prop("checked"),
            nomusuario: $("#txtnomusuario").val(),
        }),
        success: function(resultado){
            if(resultado.respuesta){
                listarUsuarios()
                /*$("#tblusuario > tbody").html("");*/
            }
            alert(resultado.mensaje);
            $("#modalNuevo").modal("hide");
        }
    })
});

function listarUsuarios(){
    $.ajax({
        type: "GET",
        url: "/backoffice/usuario/listar",
        dataType: "json",
        success: function(resultado){
            $("#tblusuario > tbody").html("");
            $.each(resultado, function(index, value){
                $("#tblusuario > tbody").append("<tr>"+
                    "<td>"+value.id+"</td>"+
                    "<td>"+value.nomusuario+"</td>"+
                    "<td>"+value.nombre+"</td>"+
                    "<td>"+value.correo+"</td>"+
                    "<td>"+value.activo+"</td>"+
                    "<td>"+value.estado+"</td>"+
                    "<td>"+
                        "<button type='button' class='btn btn-info btnactualizar'" +
                            " data-usuarioid='"+value.id+"'"+
                            " data-nomusuario='"+value.nomusuario+"'"+
                            " data-usuarionombre='"+value.nombre+"'"+
                            " data-usuariocorreo='"+value.correo+"'"+
                            " data-usuariopassword='"+value.password+"'"+
                            " data-usuarioactivo='"+value.activo+"'"+
                            " data-usuarioestado='"+value.estado+"'"+
                            "><i class='fas fa-edit'></i></button></td></tr>"
                );
            })
        }
    })
};

