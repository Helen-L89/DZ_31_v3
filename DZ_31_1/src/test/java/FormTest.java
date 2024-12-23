import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
//import org.openqa.selenium.firefox.GeckoDriverService;
//import org.openqa.selenium.firefox.service.GeckoDriverService;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class FormTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        // Используем WebDriverManager для автоматической настройки geckodriver
        WebDriverManager.firefoxdriver().setup();

        // Создаем экземпляр драйвера Firefox с опциями
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--fullscreen");

        // Инициализация FirefoxDriver
        driver = new FirefoxDriver(options);
    }

    @Test
    public void testFormSubmission() {
        // Открытие страницы формы
        driver.get("https://otus.home.kartushin.su/form.html");

        // Заполнение полей формы
        WebElement nameField = driver.findElement(By.name("username"));
        WebElement emailField = driver.findElement(By.name("email"));
        WebElement passwordField = driver.findElement(By.name("password"));
        WebElement confirmPasswordField = driver.findElement(By.name("confirm_password"));
        WebElement birthDateField = driver.findElement(By.name("birthdate"));
        WebElement languageLevelField = driver.findElement(By.name("language_level"));

        // Заполнение данных
        nameField.sendKeys("Иван Иванов");
        emailField.sendKeys("ivan.ivanov@example.com");
        passwordField.sendKeys("password123");
        confirmPasswordField.sendKeys("password123");
        birthDateField.sendKeys("1990-01-01");
        languageLevelField.sendKeys("Intermediate");

        // Проверка совпадения паролей
        Assertions.assertEquals(passwordField.getAttribute("value"), confirmPasswordField.getAttribute("value"), "Пароли не совпадают!");

        // Нажатие кнопки отправки
        // Нахождение кнопки по значению атрибута "value" и клик по ней
        WebElement submitButton = driver.findElement(By.xpath("//input[@type='submit' and @value='Зарегистрироваться']"));
        submitButton.click();


        // Проверка успешной отправки формы и вывода данных
        WebElement output = driver.findElement(By.id("output")); // Предполагаем, что результаты выводятся в элемент с id "output"
        Assertions.assertNotNull(output, "Не удалось найти вывод данных!");
        System.out.println("Данные формы:");
        System.out.println(output.getText());
    }

    @AfterEach
    public void tearDown() {
        // Закрытие браузера
        if (driver != null) {
            driver.quit();
        }
    }
} 

