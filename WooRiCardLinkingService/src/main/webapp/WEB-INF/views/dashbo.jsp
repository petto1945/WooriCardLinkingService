<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <title>Geppetto 대시보드</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/gpt_dashboard.css">
</head>
<body>
<div class="gpt_dashboard">
    <section class="gd-group01">
        <div class="gd-row">
            <div class="gd-total">
                <div>
                    <strong class="todayTotalCount">0</strong>
                    <h3>금일 챗봇 누적고객수</h3>
                </div>
            </div>
            <div class="gd-total v1">
                <div>
                    <strong class="useCount">0</strong>
                    <h3>현재 이용 누적고객수</h3>
                </div>
            </div>
        </div>
        <div class="gd-row02">
            <h3>금일 대표질의 TOP5</h3>
            <ul class="gd-rank normal">
                <li title="-">-</li>
                <li title="-">-</li>
                <li title="-">-</li>
                <li title="-">-</li>
                <li title="-">-</li>
            </ul>
        </div>
        <div class="gd-row02">
            <h3>금일 시나리오 TOP5</h3>
            <ul class="gd-rank scenario">
                <li title="-">-</li>
                <li title="-">-</li>
                <li title="-">-</li>
                <li title="-">-</li>
                <li title="-">-</li>
            </ul>
        </div>
    </section>
    <section class="gd-group02">
        <div class="gd-row">
            <div>
                <h3>금일 챗봇 질의건수</h3>
                <strong class="generalCount">0</strong>
            </div>
        </div>
        <div class="gd-row v1">
            <div>
                <h3>금일 챗봇 시나리오 건수</h3>
                <strong class="scenarioCount">0</strong>
            </div>
        </div>
        <div class="gd-row">
            <div>
                <h3>금일 챗봇 시나리오 진행중 건수</h3>
                <strong class="useScenarioCount">0</strong>
            </div>
        </div>
    </section>
    <section class="gd-group03">
        <h3>유입채널별인입 고객수</h3>
        <table class="tbl-type">
            <colgroup>
                <col>
                <col style="width:12%">
                <col style="width:12%">
                <col style="width:12%">
                <col style="width:12%">
                <col style="width:12%">
                <col style="width:12%">
                <col style="width:12%">
            </colgroup>
            <thead>
            <tr>
                <th></th>
                <th>앱</th>
                <th>모바일웹</th>
                <th>PC웹</th>
                <th>FDS</th>
                <th>ARS</th>
                <th>카카오</th>
                <th>합계</th>
            </tr>
            </thead>
            <tbody>
            <tr class="inFlowChannel1">
                <th>금일누적</th>
                <td>0</td>
                <td>0</td>
                <td>0</td>
                <td>0</td>
                <td>0</td>
                <td>0</td>
                <td>0</td>
            </tr>
            <tr class="inFlowChannel2">
                <th>현재이용고객</th>
                <td>0</td>
                <td>0</td>
                <td>0</td>
                <td>0</td>
                <td>0</td>
                <td>0</td>
                <td>0</td>
            </tr>
            </tbody>
        </table>
    </section>
    <section class="gd-group03">
        <h3>로그인수단별인입 고객수</h3>
        <table class="tbl-type">
            <colgroup>
                <col>
                <col style="width:13.8%">
                <col style="width:13.8%">
                <col style="width:13.8%">
                <col style="width:13.8%">
                <col style="width:13.8%">
                <col style="width:13.8%">
            </colgroup>
            <thead>
            <tr>
                <th></th>
                <th>로그인</th>
                <th>회원인증(카드번호)</th>
                <th>회원인증(휴대폰번호)</th>
                <th>닉네임</th>
                <th>ARS인증</th>
                <th>합계</th>
            </tr>
            </thead>
            <tbody>
            <tr class="loginMeans1">
                <th>금일누적</th>
                <td>0</td>
                <td>0</td>
                <td>0</td>
                <td>0</td>
                <td>0</td>
                <td>0</td>
            </tr>
            <tr class="loginMeans2">
                <th>현재이용고객</th>
                <td>0</td>
                <td>0</td>
                <td>0</td>
                <td>0</td>
                <td>0</td>
                <td>0</td>
            </tr>
            </tbody>
        </table>
    </section>
</div>
<script src="/resources/js/jquery-3.3.1.js"></script>
<script src="/resources/js/dashbord.js"></script>
<!--//Dashboard-->
</body>
</html>