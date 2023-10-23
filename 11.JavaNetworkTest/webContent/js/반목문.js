let v_div = document.querySelector('div')
// 1부터 10까지의 합구하기
function f_mun1() {
    let sum = 0;
    for (let i = 1; i <= 10; i++) {
        sum += i;
    }
    console.log(sum);
    v_div.innerText = sum;
}
// 1부터 200까지 짝수의 합
function f_mun2() {
    let sum = 0;
    /* for(let i =2; i<=200; i+=2){
         sum += i;
     }*/
    /*for(let i =1; i<=200; i++) {
        // 짝수조건
        if(i%2==0) {
            sum += i;
        }
    }*/
    for (let i = 1; i <= 200; i++) {
        // 짝수조건
        if (i % 2 != 0) continue;
        sum += i;
    }
    v_div.innerText = sum;
}
// 입력값을 더하고 0입력시 누적값 출력
function f_mun3() {
    let sum = 0; // 값을 담을 변수
    let str = ""; // 입력된 값을 문자열로 담을 변수
    while (1) {

        let rst = parseInt(prompt("값을 입력하세요")); // 반환값이 String 타입이므로 연산을 위해 정수형을 변환필요
        // 무한 반복문일 경우 반드시 빠져나올 조건을 먼저 정의할 것
        if (rst == 0) break;
        sum += rst;
        str += rst + " ";
    }
    // 출력형태
    let result = "입력된 수 : " + str + "<br>";
    result += "입력된 값의 합 : " + sum;
    v_div.innerHTML = result; // 정보 중 태그를 포함하므로 화면에 태그를 처리해줄 속성으로 대입
}
// (중첩 for문) 1~10까지의 각 i와 k를 더한 합이 3의 배수인 값 출력
function f_mun4() {
    let rst = 0;
    let result = "";
    for (let i = 1; i <= 10; i++) {
        for (let k = 1; k <= 10; k++) {
            // i와 k를 더한 합이 3의 배수인 값
            if ((i + k) % 3 == 0) {
                rst += (i + k); // 총합
                result += i + "+" + k + "=" + (i + k) + "<br>"; // 조건에 해당하는 수와 그 합
            }
        }
    }
    v_div.innerHTML = result;
}
// 1~100까지 2와 3의 배수인 값
function f_mun5() {
    let sum = "";
    for(let i=1; i<100; i++) {
        // 2와 3의 배수
        if(i%2 == 0 && i%3 == 0) sum += i+ " "; // 값을 더하지 않고 각 문자로 변수에 담기
    }
    v_div.innerHTML = sum;
}
// 두 수의 합이 100 이상일 때 출력(continue이용, 두수 모두 0일 때 종료)
function f_mun6() {
    let result = ""; // 초기화하지 않으면 브라우저 엔진에서 자체적으로 없는 값(undefined)을 세팅
    while(1) {
        let su1 = parseInt(prompt("1번 수를 입력하세요")); // String => number 형변환
        let su2 = parseInt(prompt("2번 수를 입력하세요"));
        
        if(su1 == 0 && su2 ==0) break; // 무한반복 빠져나올 조건

        // 두수의 합이 100 이상일 때 - continue
        let sum = su1+su2;
        if(sum < 100) continue;

        result += su1 + "+" + su2 + "=" + sum +"<br>";
    }
    v_div.innerHTML = result;
}