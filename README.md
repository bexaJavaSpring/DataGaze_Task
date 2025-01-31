Menda 3ta controller class lari bor. Bular 1) AuthController, 2) WeatherController va qo'shimcha authentication uchun RoleController
AuthController da 3ta API ko'targanman. 1) login, 2) register 3) logout
register() method -> User ni registratsiya qilganman, unga default USER roli berganman va bazaga saqlaganman.
login() method -> registratsiyadan o'tgan userlar login qip kirsa ularga access_token va refresh_token beriladi va authenikatsiyadan o'tadi.Ular keyin ob-havo ma'lumotlarini oladigan API ga access lari bo'ladi.
WeatherController da 1 ta API ko'targanman, ya'ni davlat nomi, protocol nomi(eg:http, https) va API_KEY kiritilsa, shu davlatning hozirgi paytdagi ob-havo ma'lumotlarini https://www.weatherapi.com/ site orqali keltiradi.
Keyin cron-job yozganman, listda o'zimdan 10 ta davlat qo'shib o'sha davlatlarni yuqoridagi site orqali ma'lumotlarni keltirib bazaga saqlaganman.  
