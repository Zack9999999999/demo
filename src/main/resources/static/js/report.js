// $(function () {
loadReports();

// });

function loadPage(pageNumber, repStatus) {
    loadReports(pageNumber, repStatus);
}

//顯示所有活動檢舉
function loadReports(pageNumber, repStatus) {

    let url = "/activityreport";

    if (repStatus !== undefined && repStatus != null) {
        url = url + '?repStatus=' + repStatus;
    }

    $.ajax({
        url: url,
        type: 'GET',
        data: {
            page: pageNumber - 1
        },
        dataType: 'json',
        success: function (activityReport) {
            let actReportHTML = "";
            let report = activityReport.content;

            //做分頁需要的
            let total = activityReport.totalElements; //總數量
            let size = activityReport.size; //每頁顯示的數據數量
            let number = activityReport.number; //第幾頁(0為第一頁)
            let totalPages = activityReport.totalPages; //總頁數
            let first = activityReport.first; //當前是第一頁為ture
            let last = activityReport.last; // 當前是最後一頁為true

            //分頁元件生成
            nextPage(totalPages, repStatus, first, last);

            let titleMap = {
                "INAPPROPRIATE": "不恰當的内容",
                "NOSAFETY": "不安全的",
                "MISLEADING": "誤導信息,對活動真實性或內容產生誤解",
                "HARASSMENT": "騷擾或欺凌",
                "OTHER": "其他"
            };

            let statusMap = {
                "1": "審核中",
                "2": "通過",
                "3": "未通過"
            };

            report.forEach(function (actReport) {
                let titleChinese = titleMap[actReport.repTitle];
                let statusChinese = statusMap[actReport.repStatus];

                actReportHTML +=
                    '<tr><td>' + actReport.repId + '</td>' +
                    '<td>' + actReport.act.actId + '</td>' +
                    '<td>' + actReport.memId + '</td>' +
                    '<td>' + titleChinese + '</td>' +
                    '<td>' + actReport.repContent + '</td>' +
                    '<td>';

                if (actReport.repPic == null) {
                    actReportHTML += '<button class="showPic btn btn-secondary btn-sm" ' +
                        'data-rep-id="' + actReport.repId + '" disabled>未提供圖片</button>';
                } else {
                    actReportHTML += '<button class="showPic btn btn-primary btn-sm" ' +
                        'data-bs-toggle="modal" data-bs-target="#exampleModal" data-bs-whatever="@getbootstrap" ' +
                        'data-rep-id="' + actReport.repId + '">顯示圖片</button>';
                }
                actReportHTML +=
                    '</td>' +
                    '<td  id="status-' + actReport.repId + '">' + statusChinese + '</td>' +
                    '<td>' + actReport.repTime + '</td>' +
                    '<td>' +
                    '<select class="changeStatus" data-rep-id="' + actReport.repId + '">' +
                    '<option value="1">審核中</option>' +
                    '<option value="2">通過</option>' +
                    '<option value="3">未通過</option>' +
                    '</select></td>' +
                    '<td>' + (actReport.empId ? actReport.empId : "無") + '</td></tr>';

            });

            $("#countReport").html(": 共" + total);
            $("#actReport").html(actReportHTML);

        },
        error: function (xhr, status, error) {
            Swal.fire(
                "有問題",
                "question"
            )
        }
    });
}

//圖片
$(document).on("click", ".showPic", function (e) {
    e.preventDefault();
    let repId = $(this).data("repId");

    $.ajax({
        url: "/activityreport/" + repId + "/pic",
        type: 'GET',
        xhrFields: {
            responseType: "blob"
        },
        success: function (pic) {
            let imgUrl = URL.createObjectURL(pic);
            $("#pic").attr("src", imgUrl);
        },
        error: function (xhr, status, error) {
            console.error("Error:" + status + " " + error);
        }
    });
});

//更新狀態
$(document).on("change", ".changeStatus", function () {
    let statusMap = {
        "1": "審核中",
        "2": "通過",
        "3": "未通過"
    };

    let repId = $(this).data("repId");

    let newStatus = {
        repStatus: $(this).val()
    };

    $.ajax({
        url: '/activityreport/' + repId,
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(newStatus),
        dataType: 'json',
        success: function (updateActivityReport) {
            let statusChinese = statusMap[updateActivityReport.repStatus]; // 从statusMap获取新状态的中文描述
            $("#status-" + repId).text(statusChinese);
            Swal.fire("更新成功")
        },
        error: function (xhr, status, error) {
            Swal.fire("更新失敗")
        }
    });
});

//依審核狀態篩選
$(".filterButton").on("click", function () {
    let repStatus = $(this).data("repStatus");

    loadReports(1, repStatus);
});

//分頁元件生成
function nextPage(totalPages, repStatus) {
    let pageHTML = "";
    for (let i = 0; i < totalPages; i++) {
        pageHTML += `<li  class="page-item">` +
            `<a class="page-link" href="#" onclick="loadPage(${i + 1}, ${repStatus})"; return flase;">${i + 1}</a>` +
            `</li>`;
    }
    $(".pagination").html(pageHTML); //插入頁碼
    $(".pagination").prepend('<li class="page-item disabled"><a class="page-link">上一頁</a></li>'); //將上一頁插入
    $(".pagination").append('<li class="page-item"><a class="page-link" href="#">下一頁</a></li>');  //將下一頁插入
}