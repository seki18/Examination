//当前页
var currentPage;
//总记录数
var totalRecord;
//显示分页条
function build_page_nav(result) {
	currentPage = result.map.pageInfo.pageNum;
	totalRecord = result.map.pageInfo.total;
	$("#page_nav").empty();

	var ul = $("<ul></ul>").addClass("pagination");

	var firstPageLi = $("<li></li>").append(
			$("<a></a>").append("首页").attr("href", "#"));
	var prePageLi = $("<li></li>").append(
			$("<a></a>").append("&laquo;").attr("href", "#"));
	if (!result.map.pageInfo.hasPreviousPage) {
		firstPageLi.addClass("disabled");
		prePageLi.addClass("disabled");
	} else {
		firstPageLi.click(function() {
			to_page(1);
		});
		prePageLi.click(function() {
			to_page(result.map.pageInfo.pageNum - 1);
		});
	}

	var nextPageLi = $("<li></li>").append(
			$("<a></a>").append("&raquo;").attr("href", "#"));
	var lastPageLi = $("<li></li>").append(
			$("<a></a>").append("尾页").attr("href", "#"));
	if (!result.map.pageInfo.hasNextPage) {
		nextPageLi.addClass("disabled");
		lastPageLi.addClass("disabled");
	} else {
		nextPageLi.click(function() {
			to_page(result.map.pageInfo.pageNum + 1);
		});
		lastPageLi.click(function() {
			to_page(result.map.pageInfo.pages);
		});
	}

	ul.append(firstPageLi).append(prePageLi);

	$.each(result.map.pageInfo.navigatepageNums, function(index, item) {
		var numLi = $("<li></li>").append(
				$("<a></a>").append(item).attr("href", "#"));
		if (result.map.pageInfo.pageNum == item) {
			numLi.addClass("active");
		}
		numLi.click(function() {
			to_page(item);
		});
		ul.append(numLi);
	});

	ul.append(nextPageLi).append(lastPageLi);

	var nav = $("<nav></nav>").append(ul);
	nav.appendTo("#page_nav");
}