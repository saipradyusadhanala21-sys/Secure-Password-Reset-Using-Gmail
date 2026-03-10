// CO-4: Event handling for form submission
document.getElementById("createForm").addEventListener("submit", function(e){

    e.preventDefault();
    
    // CO-3: Variables and string manipulation
    let email = document.getElementById("newEmail").value.trim().toLowerCase();
    let password = document.getElementById("newPassword").value;
    
    // CO-3: Regular Expression validation
    let gmailPattern = /^[a-zA-Z0-9._%+-]+@gmail\.com$/;
    
    if(!gmailPattern.test(email)){
    alert("Please enter a valid Gmail address");
    return;
    }
    
    // CO-3: Local storage used as simple database
    localStorage.setItem(email, password);
    
    alert("Account Created Successfully for " + email);
    
    });
    
    // CO-4: DOM manipulation to show create page
    function showCreate(){
    document.getElementById("homePage").style.display="none";
    document.getElementById("createPage").style.display="block";
    }
    
    // CO-3: Variables used for OTP logic
    let generatedOTP;
    let resetEmailGlobal;
    
    // CO-4: Reset password form event
    document.getElementById("resetForm").addEventListener("submit", function(e){
    
    e.preventDefault();
    
    let email = document.getElementById("resetEmail").value.trim().toLowerCase();
    
    let gmailPattern = /^[a-zA-Z0-9._%+-]+@gmail\.com$/;
    
    if(!gmailPattern.test(email)){
    alert("Enter a valid Gmail address");
    return;
    }
    
    // CO-3: Retrieving data from local storage
    let storedPassword = localStorage.getItem(email);
    
    if(storedPassword){
    
    resetEmailGlobal = email;
    
    // CO-3: Random OTP generation
    generatedOTP = Math.floor(100000 + Math.random() * 900000);
    
    alert("OTP sent to your email: " + generatedOTP);
    
    // CO-4: DOM manipulation
    document.getElementById("otpSection").style.display = "block";
    
    }
    else{
    alert("Account not found");
    }
    
    });
    
    // CO-4: Page navigation
    function showReset(){
    document.getElementById("homePage").style.display="none";
    document.getElementById("resetPage").style.display="block";
    }
    
    // CO-4: DOM manipulation to navigate pages
    function goHome(){
    
    document.getElementById("createPage").style.display="none";
    document.getElementById("resetPage").style.display="none";
    document.getElementById("accountsPage").style.display="none";
    document.getElementById("homePage").style.display="block";
    
    let otp = document.getElementById("otpSection");
    
    if(otp){
    otp.style.display="none";
    }
    
    }
    
    // CO-3 & CO-4: OTP verification logic
    function verifyOTP(){
    
    let userOTP = document.getElementById("userOTP").value;
    
    if(parseInt(userOTP) === generatedOTP){
    
    alert("OTP Verified Successfully");
    
    document.getElementById("otpSection").style.display="none";
    document.getElementById("newPasswordSection").style.display="block";
    
    }
    else{
    alert("Invalid OTP");
    }
    
    }
    
    // CO-3: Password strength checking
    function checkStrength(){
    
    let password = document.getElementById("newPassword").value;
    let strength = document.getElementById("strength");
    
    if(password.length === 0){
    strength.innerText="";
    }
    else if(password.length < 6){
    strength.innerText="Weak Password";
    strength.style.color="red";
    }
    else if(!password.match(/[A-Z]/)){
    strength.innerText="Add uppercase letter";
    strength.style.color="orange";
    }
    else if(!password.match(/[0-9]/)){
    strength.innerText="Add number";
    strength.style.color="orange";
    }
    else{
    strength.innerText="Strong Password";
    strength.style.color="green";
    }
    
    }
    
    // CO-4: Toggle password visibility
    function togglePassword(){
    
    let p = document.getElementById("newPassword");
    
    if(p.type === "password"){
    p.type="text";
    }
    else{
    p.type="password";
    }
    
    }
    
    // CO-3: Arrays used to store account list
    function showAccountsPage(){
    
    document.getElementById("homePage").style.display="none";
    document.getElementById("accountsPage").style.display="block";
    
    displayAccounts();
    
    }
    
    // CO-3: Arrays and objects (localStorage keys)
    function displayAccounts(){
    
    let list = document.getElementById("accountList");
    
    list.innerHTML="";
    
    let emails = Object.keys(localStorage);
    
    if(emails.length === 0){
    list.innerHTML="<p>No registered Gmail accounts</p>";
    }
    else{
    
    emails.forEach(function(email){
    
    list.innerHTML += `
    <div>
    <p>${email}</p>
    <button onclick="deleteAccount('${email}')">Delete</button>
    </div>
    `;
    
    });
    
    }
    
    }
    
    // CO-3: Delete account functionality
    function deleteAccount(email){
    
    localStorage.removeItem(email);
    
    alert("Account deleted: " + email);
    
    displayAccounts();
    
    }
    
    // CO-3: Updating password
    function updatePassword(){
    
    let newPassword = document.getElementById("resetNewPassword").value;
    
    if(newPassword.length < 6){
    alert("Password must be at least 6 characters");
    return;
    }
    
    localStorage.setItem(resetEmailGlobal, newPassword);
    
    alert("Password updated successfully");
    
    goHome();
    
    }