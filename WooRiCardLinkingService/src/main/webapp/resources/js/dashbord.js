$(document).ready(function() {
  var url = 'http://localhost:8019/common/stats/';

  (function getTodayChatbotCount() {
    $.ajax({
      type: 'POST',
      url: `${url}todaychatbotcount`,
      data: '',
	  dataType: 'json',
      success: function(data) {
        console.log('getTodayChatbotCount', data);
        
        $('.generalCount')[0].innerText = numberWithCommas(data.generalCount);
        $('.scenarioCount')[0].innerText = numberWithCommas(data.scenarioCount);
		$('.useScenarioCount')[0].innerText = numberWithCommas(data.useScenarioCount);
	  },
	  complete: setTimeout(getTodayChatbotCount, 3*10e4),
      error: function(request, status, error) {
        alert(request.responseText);
      }
    });
  })();

  (function getInFlowChCustom() {
    $.ajax({
      type: 'POST',
      url: `${url}inflowchannelcustomer`,
      data: '',
      dataType: 'json',
      success: function(data) {
        console.log('getInFlowChCustom', data);

        var inFlowChannel1 = $('.inFlowChannel1')[0].children;
        var inFlowChannel2 = $('.inFlowChannel2')[0].children;

        inFlowChannel1[1].innerText = numberWithCommas(data.inFlowChannel[0].app);
        inFlowChannel1[2].innerText = numberWithCommas(data.inFlowChannel[0].mweb);
        inFlowChannel1[3].innerText = numberWithCommas(data.inFlowChannel[0].pcweb);
        inFlowChannel1[4].innerText = numberWithCommas(data.inFlowChannel[0].fds);
        inFlowChannel1[5].innerText = numberWithCommas(data.inFlowChannel[0].ars);
        inFlowChannel1[6].innerText = numberWithCommas(data.inFlowChannel[0].kakao);
		inFlowChannel1[7].innerText = numberWithCommas(data.todayTotalCount);
		
        inFlowChannel2[1].innerText = numberWithCommas(data.inFlowChannel[1].app);
        inFlowChannel2[2].innerText = numberWithCommas(data.inFlowChannel[1].mweb);
        inFlowChannel2[3].innerText = numberWithCommas(data.inFlowChannel[1].pcweb);
        inFlowChannel2[4].innerText = numberWithCommas(data.inFlowChannel[1].fds);
        inFlowChannel2[5].innerText = numberWithCommas(data.inFlowChannel[1].ars);
        inFlowChannel2[7].innerText = numberWithCommas(data.useCount);

        $('.todayTotalCount')[0].innerText = numberWithCommas(data.todayTotalCount);
		$('.useCount')[0].innerText = numberWithCommas(data.useCount);
	  },
	  complete: setTimeout(getInFlowChCustom, 3*10e4),
      error: function(request, status, error) {
        alert(request.responseText);
      }
    });
  })();

  (function getInComingLogin() {
    $.ajax({
      type: 'POST',
      url: `${url}incomingbylogin`,
      data: '',
      dataType: 'json',
      success: function(data) {
        console.log('getInComingLogin', data);

        var loginMeans1 = $('.loginMeans1')[0].children;
        var loginMeans2 = $('.loginMeans2')[0].children;

        loginMeans1[1].innerText = numberWithCommas(data.loginMeans[0].loginChannel);
        loginMeans1[2].innerText = numberWithCommas(data.loginMeans[0].loginCard);
        loginMeans1[3].innerText = numberWithCommas(data.loginMeans[0].loginHp);
        loginMeans1[4].innerText = numberWithCommas(data.loginMeans[0].loginNick);
        loginMeans1[5].innerText = numberWithCommas(data.loginMeans[0].loginArs);
		loginMeans1[6].innerText = numberWithCommas(data.loginTotalCount);
		
        loginMeans2[1].innerText = numberWithCommas(data.loginMeans[1].loginChannel);
        loginMeans2[2].innerText = numberWithCommas(data.loginMeans[1].loginCard);
        loginMeans2[3].innerText = numberWithCommas(data.loginMeans[1].loginHp);
        loginMeans2[4].innerText = numberWithCommas(data.loginMeans[1].loginNick);
        loginMeans2[5].innerText = numberWithCommas(data.loginMeans[1].loginArs);
        loginMeans2[6].innerText = numberWithCommas(data.loginUseCount);
	  },
	  complete: setTimeout(getInComingLogin, 3*10e4),
      error: function(request, status, error) {
        alert(request.responseText);
      }
    });
  })();

  (function getNoScTop5() {
    $.ajax({
      type: 'POST',
      url: `${url}nosctop5`,
      data: '',
      dataType: 'json',
      success: function(data) {
        console.log('getNoScTop5', data);

        var normal = $('.normal')[0].children;
        var scenario = $('.scenario')[0].children;

        for (var i = 0; i < 5; i++) {
            if(!!data.top5.normal[i]) {
          	normal[i].innerText = data.top5.normal[i];
              normal[i].title = data.top5.normal[i];
            } else {
          	normal[i].innerText = '-';
          	normal[i].title = '-';
            }
          }

          for (var i = 0; i < 5; i++) {
            if(!!data.top5.scenario[i]) {
              scenario[i].innerText = data.top5.scenario[i];
              scenario[i].title = data.top5.scenario[i];
            } else {
          	scenario[i].innerText = '-';
              scenario[i].title = '-';
            }
          }
	  },
	  complete: setTimeout(getNoScTop5, 3*10e4),
      error: function(request, status, error) {
        alert(request.responseText);
      }
    });
  })();
  
});

function numberWithCommas(x) {
  return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
}
