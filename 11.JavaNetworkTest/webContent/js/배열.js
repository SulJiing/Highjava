// 자바스크립트로 요소에 접근해서 제어할 때 대상요소를 명확하게 지정해야 함!
let v_div = document.getElementsByTagName('div'); // 대상이 1개이더라도 index로 대상을 확정할 것
// let v_div2 = document.querySelectorAll('div')[1];
// let v_div3 = document.querySelectorAll('div')[2];
// let v_div = document.querySelector('div'); // 대상요소가 여러개일 때 가장 첫번째 항목만 반환하는 특징

// 리터럴 방식으로 배열생성
function f_arr1() {
    let v_arr = [1, 3.4, "string", '문자', null, false, undefined, "ㅎㅎㅎ"];
    console.log(v_arr);
    v_div[0].innerText = v_arr; // 에러
}

// 생성자 방식으로 배열생성 1
function f_arr2() {
    let v_arr1 = new Array("배추", "도사", "무도사");

    let rst = "";
    for (let i = 0; i < v_arr1.length; i++) {
        if(i>0) rst += "~~~";
        rst += v_arr1[i];
    }

    v_div[1].textContent = rst;
}

// 생성자 방식으로 배열생성 2
function f_arr3() {
    let v_arr2 = new Array();
    v_arr2[0] = "레이디바운스";
    v_arr2[1] = "잼리퍼블릭";
    v_arr2[2] = "원밀리언";
    v_arr2[3] = "올폴로";

    let rst = "";
    for(let v in v_arr2) { // 배열 대상이므로 변수에 index정보가 들어있음
        if(v<0) rst += ", ";
        rst += v_arr2[v]; // index로 접근해서 값얻음
    }
    v_div[2].innerText = rst;
}