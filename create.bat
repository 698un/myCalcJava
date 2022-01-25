DB_url = jdbc:mysql://remotemysql.com:3306/WxBvrB2mJT
DB_username = WxBvrB2mJT
DB_password = fvWaOOU2Vx
DB_driver = com.mysql.cj.jdbc.Driver


size of table 320 * 160 sm
size of ball 6 sm

Эта команда преобразует все картинки из текущей директории (названные image1.jpg, image2.jpg и т.д.) в видеофайл video.mpg

(примечание переводчика: мне больше нравится такой формат:
ffmpeg -r 12 -y -i "image_%010d.png" output.mpg