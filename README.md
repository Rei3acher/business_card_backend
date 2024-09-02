# visitenkarten-backend
Backend for the visitenkarten project

## Prozessübersicht
## REST API
### GET /assingment/uuid
* Location: CUSAssignmentResource.java
* Parameter: uuid (UserUUID)
* Response: CusAssingment
* Description: 

### POST /cus/addUser
* Location: CUSRessource.java
* Parameter: accountName (String)
* Response: UserUUID
* Description:

## Service
### AssignmentService
* Location: AssignmentService.java
* Method:
  * userExists(uuid (UUID)):boolean
  * getAccountName(uuid (UUID)):String
  * addNewUser(accountName (String)):UUID
* Description:


### CusService
* Location: CusService.java
* Method:
  * getUserData(username (String)):CusAssignment
* Description:

### QRCodeService
* Location: QRCodeService.java
* Method:
  * generateQRCode(uuid (UUID)):BufferedImage
  * getQrCodeWithImg(uuid (UUID)):BufferedImage
  * scaleOverlay(qrCode (BufferedImage), logo (BufferedImage)):BufferedImage
* Description:

## Model
### CusAssignment
* Location: CusAssignment.java
* Attributes:
  * account_name (String)
  * firstName (String)
  * sureName (String)
  * title (String)
  * sex (String)
  * initials (String)
  * telephoneNumber (String)
  * birthYear (int)
  * department (String)
  * position (String)
  * clusterName (String)
  * seniority (LocalDate)
* Description:

### UserUUID
* Location: UserUUID.java
* Attributes:
  * uuid (UUID)
* Description:

