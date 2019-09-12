*** JDK installing on Windows ***
Step 1. Download JDK
Step 2. Install JDK and JRE
Step 3. Include JDK's "bin" Directory in the PATH
Step 4: Verify the JDK Installation

***
Step 1. Download WebDriver
Step 2. Set System property or PATH or locate it in C:\Windows\System32

***
12.09.2019 

Lesson 9 Allure

Столкнулся с проблемой. Driver Manager выкачал драйвер новой версии и не смог его загрузить, нужно было перезапустить версию.

Установка Allure с загрузкой отчета через браузер через allure serve
Версия Allure comandline должна совпадать с версией прописанной в pom (версии первая или вторая)
1)ссылка для allure install - https://docs.qameta.io/allure/
на сайте для Windoes предлагается установить allure программой Scoope или установить вручную
идем на Maven Central -> Выбираем последнюю версию Allure (2.13.0) -> allure-commandline-2.13.0.zip (прямая ссылка http://repo.maven.apache.org/maven2/io/qameta/allure/allure-commandline/2.13.0/allure-commandline-2.13.0.zip)
качаем файлик.

2)Добавляем путь C:\Users\Dell\Downloads\allure-commandline-2.13.0\allure-2.13.0\bin в переменную в path

3)В командной строке прописываем allure -- version, должен прийти ответ

4)Latest не загрузилось. Идем в https://mvnrepository.com и ищем allure-testng качаем dependency на последнюю версию

5)aspectj.versoin не подгружалось, подгрузилось после mvn clean install

6)Пробуем как работает команда формирования отчета:
в терминале Intelij или CMD набираем allure serve target/allure-results

Нюанс, если ты прописывал переменные они могли не подхватится СMD и Intelij  поэтому если отчет не запустился
нужно перегрузить сессию CMD или перегрузить Intelij

Должно загрузится окно браузера с пустым отчетом.
Чтобы выйти из отчета нажать Ctrl + C, чтобы не занимал порт, отчет поднимается на http сервер
7)Аннотации, которые используются для вывода разной информации в отчет:
@Epic(value = "Login")
@Feature(value = "Auth") - уровень иерархии с отображением обьединяющей темы
@Story(value = "Login") - уровень иерархии с отображением обьединяющей темы
@Epics(value = {@Epic(value = "Математика"), @Epic(value = "Геометрия")})
@Features(value = {@Feature(value = "Тригонометрия"), @Feature(value = "Простые математические операции")})
@Stories(value = {@Story(value = "Синус"), @Story(value = "Синусоида")})
  
  
@Severity - — аннотация, размещаемая над тестом. Позволяет указать уровень критичности функционала, проверяемого автотестом. Данная аннотация принимает параметр «value», который может быть одним из элементов enum SeverityLevel: BLOCKER, CRITICAL, NORMAL, MINOR или TRIVIAL.
  
@Step(value = "Login as user {0} with passport {1}") 
  {0} и {1} это параметра первый и второй которые передаются в аргументах над которым стоит @Step
  )
  
 запуск этого теста через maven через CMD
 mvn clean test -DsuiteXmlFile=login-tests.xml
