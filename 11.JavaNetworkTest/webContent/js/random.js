// 랜덤 정수를 발생시키는 함수
function f_random(minVal, maxVal) {
   return Math.floor(Math.random() * (maxVal - minVal + 1) + minVal);
}