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

    let data = {
      "tz": $('#tz').val(),
      "name": $('#name').val(),
      "lastName": $('#secondName').val(),
      "birthday": $('#birthday').val(),
      "startWork": $('#startWor').val()
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


function clearDialog() {
  $('#tz').val('')
  $('#name').val('')
  $('#secondName').val('')
  $('#startWork').val('')
  $('#birthday').val('')
}
});

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
      "defaultContent": "<button class='delete-button'>DELETE</button>",

    }],
    "aoColumns": [
      {"mData": "tz"},
      {"mData": "name"},
      {"mData": "lastName"},
      {"mData": "workExperience"},
      {"mData": "birthday"},
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

$.ajax({
  url: 'https://jsonip.com/',
  type: 'GET',
  success: function (resp) {
    var outputText = 'ip: ' + resp.ip;
    console.log(resp.ip + " ip")
    $('#ip').append(outputText);
  }
})

$.ajax({
  type: "OPTIONS", url: "/", complete: function (x) {
    console.log(x.getResponseHeader("Date"));
  }
})



