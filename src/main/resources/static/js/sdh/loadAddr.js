document.getElementById('loadAddrGu').addEventListener('change', function () {
  let gu = this.value
  // 시.군.구 선택 시 읍.면.동 셀렉트 박스 보이게 설정
  let dongSelect = document.getElementById('loadAddrDong')
  dongSelect.style.display = 'block'
  fetch('/test/getDong?gu=' + gu)
    .then(response => response.json()) // JSON 데이터를 받음
    .then(data => {
      dongSelect.innerHTML = '' // 기존 옵션 초기화
      // 기본 옵션 추가
      let defaultOption = document.createElement('option')
      defaultOption.value = ''
      defaultOption.textContent = '읍.면.동 을 선택해주세요.'
      dongSelect.appendChild(defaultOption)
      
      data.forEach(dong => {
        let option = document.createElement('option')
        option.value = dong
        option.textContent = dong
        dongSelect.appendChild(option)
      })
    })
    .catch(error => console.error('Error:', error))
})
document.getElementById('loadAddrDong').addEventListener('change', function () {
  let dong = this.value
  let roSelect = document.getElementById('loadAddrRo')
  roSelect.style.display = 'block'
  let gu = document.getElementById('loadAddrGu').value // gu 값 가져오기
  fetch(`/test/getRo?gu=${gu}&dong=${dong}`) // gu와 dong 을 함께 전달
    .then(response => response.json())
    .then(data => {
      roSelect.innerHTML = '' // 기존 옵션 초기화
      let defaultOption = document.createElement('option')
      defaultOption.value = ''
      defaultOption.textContent = '도로명 을 선택해주세요.'
      roSelect.appendChild(defaultOption)
      data.forEach(ro => {
        let option = document.createElement('option')
        option.value = ro
        option.textContent = ro
        roSelect.appendChild(option)
      })
    })
    .catch(error => console.error('Error:', error))
})
document.getElementById('loadAddrRo').addEventListener('change', function () {
  let ro = this.value
  let dong = document.getElementById('loadAddrDong').value
  let gu = document.getElementById('loadAddrGu').value
  let mainNumSelect = document.getElementById('loadAddrMainNum')
  fetch(`/test/getMainNum?gu=${gu}&dong=${dong}&ro=${ro}`) // gu와 dong 을 함께 전달
    .then(response => response.json())
    .then(data => {
      if (!(data.length === 1 && data[0] === '0'))
      {
        mainNumSelect.style.display = 'block'
        mainNumSelect.innerHTML = '' // 기존 옵션 초기화
        let defaultOption = document.createElement('option')
        defaultOption.value = ''
        defaultOption.textContent = '메인도로 숫자를 입력해주세요.'
        mainNumSelect.appendChild(defaultOption)
      }
      data.forEach(mainNum => {
        let option = document.createElement('option')
        option.value = mainNum
        option.textContent = mainNum
        mainNumSelect.appendChild(option)
      })
    })
    .catch(error => console.error('Error:', error))
})
document.getElementById('loadAddrMainNum').addEventListener('change', function () {
  let mainNum = this.value
  let dong = document.getElementById('loadAddrDong').value
  let gu = document.getElementById('loadAddrGu').value
  let ro = document.getElementById('loadAddrRo').value
  let subNumSelect = document.getElementById('loadAddrSubNum')
  fetch(`/test/getSubNum?gu=${gu}&dong=${dong}&ro=${ro}&mainNum=${mainNum}`)
    .then(response => response.json())
    .then(data => {
      if (!(data.length === 1 && data[0] === '0'))
      {
        subNumSelect.style.display = 'block'
        subNumSelect.innerHTML = '' // 기존 옵션 초기화
        let defaultOption = document.createElement('option')
        defaultOption.value = ''
        defaultOption.textContent = '서브도로 숫자를 입력해주세요.'
        subNumSelect.appendChild(defaultOption)
      }
      data.forEach(subNum => {
        let option = document.createElement('option')
        option.value = subNum
        option.textContent = subNum
        subNumSelect.appendChild(option)
      })
    })
    .catch(error => console.error('Error:', error))
})