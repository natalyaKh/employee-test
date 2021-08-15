function formatBirthdayDate() {
  var d = new Date(),
    month = '' + (d.getMonth() + 1),
    day = '' + d.getDate(),
    year = d.getFullYear() - 15;

  if (month.length < 2)
    month = '0' + month;
  if (day.length < 2)
    day = '0' + day;
  return [year, month, day].join('-');
}

function formatDate() {
  var d = new Date(),
    month = '' + (d.getMonth() + 1),
    day = '' + d.getDate(),
    year = d.getFullYear();

  if (month.length < 2)
    month = '0' + month;
  if (day.length < 2)
    day = '0' + day;

  return [year, month, day].join('-');
}


document.getElementById("birthday")
  .setAttribute("max",formatBirthdayDate())

document.getElementById("startWork")
  .setAttribute("max",formatDate())


$(function () {

  $('#dialog').dialog({
    buttons: [
      {text: "save employee", click: addDataToTable},
      {text: "cancel", click: cancel},
    ],
    modal: true,
    autoOpen: false,
    maxWidth: 500,
    responsive: true,
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

    if (!$('#tz').val() || $('#tz').val()=='') {
      document.getElementById("tz")
        .setAttribute("style","border: 1px solid red;")
      document.getElementById("tz")
        .setAttribute("placeholder","tz is required field")
      // alert('field Tz is required and should contains mi 4 symbols');
    }else if(!$('#name').val() || $('#name').val()=='') {
      document.getElementById("name")
        .setAttribute("style","border: 1px solid red;")
      document.getElementById("name")
        .setAttribute("placeholder","name is required field")
    }else if(!$('#lastName').val() || $('#lastName').val()=='') {
      document.getElementById("lastName")
        .setAttribute("style","border: 1px solid red;")
      document.getElementById("lastName")
        .setAttribute("placeholder","last name is required field")
    }
    else if(!$('#startWork').val() || $('#startWork').val() == '') {
      document.getElementById("startWork")
        .setAttribute("style", "border: 1px solid red;")
      document.getElementById("startWork")
        .setAttribute("placeholder", "start work name is required field")
    }else if(!$('#birthday').val() || $('#birthday').val() == '') {
      document.getElementById("birthday")
        .setAttribute("style", "border: 1px solid red;")
      document.getElementById("birthday")
        .setAttribute("placeholder", "birthday name is required field")
    } else {
      let data = {
        "tz": $('#tz').val(),
        "name": $('#name').val(),
        "lastName": $('#lastName').val(),
        "birthday": $('#birthday').val(),
        "startWork": $('#startWork').val()
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
        error: function (resp) {
          var error = JSON.parse(resp.responseText);
          console.log(error.error)
          alert(error.error)
        }
      })
      clearDialog()
      $('#dialog').dialog("close");
    }
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
      {"mData": "birthday"},
      {"mData": "workExperience"},

      {"mData": "action"}
    ]
  })

  $('#employeesTable tbody').on('click', 'button', function () {
    var data = table.row($(this).parents('tr')).data()
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
    $('#ip').append(outputText);
  }
})

$.ajax({
  type: "OPTIONS", url: "/", complete: function (x) {
    console.log(x.getResponseHeader("Date"));
  }
})

$(document).ready(function () {
  var timeDifference;
  $.ajax({
    url: '/time',
    type: 'GET',
    dataType: 'json',
    success: function(response) {
      timeDifference = new Date().getTime() - new Date(response).getTime();
      getdate();
    }
  });

  function setFirstZero(value) {
    if (value < 10) {
      value = "0" + value;
    }
    return value;
  }

  function getdate(){
    var servertime = new Date();
    servertime.setMilliseconds(servertime.getMilliseconds() - timeDifference);
    var day = setFirstZero(servertime.getDate());
    var month = setFirstZero(servertime.getMonth() + 1);
    var year = servertime.getFullYear();
    var hours = setFirstZero(servertime.getHours());
    var minutes = setFirstZero(servertime.getMinutes());
    var seconds = setFirstZero(servertime.getSeconds());
    $(".timer").text(day + "/" + month + "/" + year + " " + hours+" : "+minutes+" : "+seconds);
    setTimeout(function(){getdate()}, 500);
  }
})


