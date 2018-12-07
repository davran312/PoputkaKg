package poputka.neobis.poputkakg.models

data class  RegistrationModel(var phone:String, var password:String, var password_repeat:String,
                              var city_id:Int, var is_driver:Boolean, var first_name:String,
                              var last_name:String, var gender:Int, var birth_date:String)