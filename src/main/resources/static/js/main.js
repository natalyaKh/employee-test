$(function () {

  $('#dialog').dialog({
    buttons: [
      {text: "save employee", click: addDataToTable},
      {text: "cancel", click: cancel},
    ],
    modal: true,
    autoOpen: false,
    width: 500,
    clear: clearDialog()
  })

  $('#show').button().click(function () {
    $('#dialog').dialog("open");
  })

  function cancel() {
    clearDialog()
    $('#dialog').dialog("close");
  }

  function addDataToTable() {
    $('#placeholder').hide();
    // var tz = $('#tz').val()
    // var name = $('#name').val()
    // var secondName = $('#secondName').val()
    // var startWork = $('#startWork').val()
    // var birthday = $('#birthday').val()

    let data = {
      "tz": $('#tz').val(),
      "name": $('#name').val(),
      "lastName": $('#secondName').val(),
      "birthday": $('#startWork').val(),
      "startWork": $('#birthday').val()
    };
    $.ajax({
      url: '/employee',
      type: 'POST',
      async: false,
      contentType: 'application/json',
      data: JSON.stringify(data),
      success: function (resp) {
        location.reload();
      },
      error:function (resp){
       var  error = JSON.parse(resp.responseText);
       console.log(error.error)
      alert(error.error)
      }
    })
    clearDialog()
    $('#dialog').dialog("close");
  }

  // function sendData() {
  //   console.log(this.tz + " senddata")
  // //   $.ajax({
  //       url: '/employee',
  //       type: 'POST',
  //       async: false,
  //       data: JSON.stringify(data)
  //       "tz": this.tz,
  //       "name": this.name,
  //       "lastName": this.lastName,
  //       "birthday": this.birthday,
  //       "startWork": this.startWork
  //     },
  //     success
  // :

  //   function (resp) {
  //     console.log(resp + " added")
  //   }
  // }

// )
// }

function clearDialog() {
  $('#tz').val('')
  $('#name').val('')
  $('#secondName').val('')
  $('#startWork').val('')
  $('#birthday').val('')
}
})
;


// $('#changeButton').click(function() {
//
//   // console.log
//   var form = $(this),
//     name = form.find('input[name:"name"]')
//   // var name = $('.exampleModalCenter').find('inpit[name="name"]').val();
//   console.log(name + " k")
//   // goods = $(this).data('goods');
//   // $("#goods").append(goods);
//
// });
// $(function() {
//   $(".btn").click(
//     function() {
//       var imgtovara = $(this).attr('exampleFormControlInput2');
//       // var nametitle = $(this).attr('data-nametitle');
//       // var pricetovar = $(this).attr('data-pricetovar');
// console.log(imgtovara);
//       // $(".tovarimg").append('<img class="img-fluid" src="' + imgtovara + '" alt="..." />');
//       // $(".tovarinfo").append('<p class="h3">' + nametitle + '</h1>');
//       // $(".tovarinfo").append('<p><strong>Цена</strong>:' + pricetovar + '</p>');
//       // $("#hide1").attr('value', nametitle);
//       // $("#hide2").attr('value', pricetovar);
//     })
// });
$(document).ready(function () {
  var table = $('#employeesTable').DataTable({
    searching: false,
    ordering: false,
    lengthMenu: [[4, 8, 12, -1], [4, 8, 12, "All"]],

    "sAjaxSource": "/employees",
    "sAjaxDataProp": "",
    "order": [[0, "asc"]],
    "columnDefs": [{
      "targets": -1,
      "data": null,
      "defaultContent": "<button class='btn btn-danger'>DELETE</button>",

    }],
    "aoColumns": [
      {"mData": "tz"},
      {"mData": "name"},
      {"mData": "lastName"},
      {"mData": "birthday"},
      {"mData": "workExperience"},
      {"mData": "action"}
    ]
  })

  $('#employeesTable tbody').on('click', 'button', function () {
    var data = table.row($(this).parents('tr')).data()
    console.log(data.tz + " iii")
    $(this).closest("tr").remove()
    deleteEmployee(data.tz);
  });
});

function deleteEmployee(id) {
  $.ajax({
    url: '/employee/' + id,
    type: 'DELETE',
    async: true,
    success: function (resp) {
      console.log(resp + " deleted")
    },
    error:function (resp){
      var  error = JSON.parse(resp.responseText);
      console.log(error.error)
      alert(error.error)
}
  })
}

