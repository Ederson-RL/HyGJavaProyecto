function eliminar(idcompra){
    swal({
        title: "Esta seguro de Eliminar?",
        text: "Once deleted, you will not be able to recover this imaginary file!",
        icon: "warning",
        buttons: true,
        dangerMode: true,
      })
      .then((OK) => {
        if (OK) {

            $.ajax({
            url:"/eliminarC/" + idcompra,
            success: function (res) {
                console.log(res);
    
            }
        });
          swal("Poof! Your imaginary file has been deleted!", {
            icon: "success",
          }).then((OK)=>{
            if(OK){
                location.href="/listarC"
            }
          });
        } else {
          swal("Your imaginary file is safe!");
     }
});
}