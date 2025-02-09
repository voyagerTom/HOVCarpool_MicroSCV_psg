
## HOVCarpool
this is side project about HOV( High-Occupancy Vehicle) solution



## HOVCarpool服務(一般乘客、使用者)，以微服務架構呈現


### HOV_psg
passenger 服務 
port:806X
功能:負責乘客 / 一般user 預計共乘

### HOV_driver
port:807X
功能: 負責司機角色，發起/取消共乘


### HOV_admin
port:808X
功能: 負責管理者角色，由地方管理者管理司機與乘客
