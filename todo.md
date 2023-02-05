# TODO - список.

Что еще можно сделать в проекте.

## 1. Изменить способ связывания сущностей

Связать сущности [BankAcc](src%2Fentity%2FBankAcc.java) и [User](src%2Fentity%2FUser.java) не по Id.
Т.е. при создании [User](src%2Fentity%2FUser.java) записать в качестве переменной
класса [BankAcc](src%2Fentity%2FBankAcc.java).
Так что бы через объект класса [User](src%2Fentity%2FUser.java) можно было обратиться к
его [BankAcc](src%2Fentity%2FBankAcc.java)
<br>Пример:

```java
BanckAcc bankAcc=new BankAcc(...);
        User user=new User(...,bankAcc);

// do somthing 
        BankAcc userBank=user.getBankAcc();
```

## 2. Изменить .csv файл.

Текущей:

| NAME   | LAST_NAME  |               ADDRESS | CITY_CODE |     PHONE_NR |           IBAN |  Balance | TYPE |
|--------|:----------:|----------------------:|----------:|-------------:|---------------:|---------:|-----:|
| Max    | Mustermann |        Hauptstrasse 1 |     10169 | 491761234567 | 50010054579654 |  3500.91 |    C |
| Maria  |  Schmidt   |     Bahnhofstrasse 91 |     10576 |              | 60060044599456 |   500.26 |    D |
| Viktor |  Neumann   | Berliner strasse. 131 |     12612 | 491736664567 | 70090134236123 |   100.00 |    C |
| Maria  |    Novo    |    Leipziger str. 131 |     10361 | 491713216548 | 70090134236123 | 13500.67 |    D |

Несмотря на то, что в старой версии есть некий тип, не понятно как его использовать. По этому нужно расширить наш csv.

Обновленный

| NAME   | LAST_NAME  |               ADDRESS | CITY_CODE |     PHONE_NR |           IBAN |      SUMM | TRANSAKTION_TYPE | BOOKING_TYP | TRANSAKTION_DATE | TRANSAKTION_NUMMER |
|--------|:----------:|----------------------:|----------:|-------------:|---------------:|----------:|-----------------:|------------:|-----------------:|-------------------:|
| Max    | Mustermann |        Hauptstrasse 1 |     10169 | 491761234567 | 50010054579654 |    700.97 |                C |           S |       2022-01-17 |                  1 |
| Maria  |  Schmidt   |     Bahnhofstrasse 91 |     10576 |              | 60060044599456 | 510000.25 |                D |           S |       2022-02-01 |                  1 |
| Viktor |  Neumann   | Berliner strasse. 131 |     12612 | 491736664567 | 70090134236987 | 123500.50 |                C |           I |       2022-04-20 |                  1 |
| Viktor |  Neumann   | Berliner strasse. 131 |     12612 | 491736664567 | 70090134236321 | 123500.50 |                D |           I |       2022-04-20 |                  1 |
| Maria  |    Novo    |    Leipziger str. 131 |     10361 | 491713216548 | 70090134236456 |   1500.90 |                C |           I |       2022-06-21 |                  2 |
| Maria  |    Novo    |    Leipziger str. 131 |     10361 | 491713216548 | 70090134236567 |   1500.90 |                D |           I |       2022-06-21 |                  2 |

TRANSAKTION_TYP может иметь два значения:
I - interne (перевод внутри банка, с карты на карту)
S - sepa (перевод из другого банка, с банка X в наш банк)

т.е. мы знаем что клиент [User](src%2Fentity%2FUser.java) выполнил операцию.
<br>Если TYPE = C значит клиент перевел сумму, если D, то счет клиента был пополнен.
<br>Если TRANSAKTION_TYP = S значит транзакция прошла с участием внешнего банка, если TRANSAKTION_TYP = I, значит
транзакция прошла внутри банка.

Для того чтобы нам можно было отслеживать саму транзакцию необходимо создать новую сущность Transaktion, c полями
SUMM, TRANSAKTION_TYPE, BOOKING_TYP, TRANSAKTION_DATE, TRANSAKTION_NUMMER
После анализа, выяснилось, что сущность [BankAcc](src%2Fentity%2FBankAcc.java) необходимо избавить от поля BALANCE. Наш
файл больше не хранит данные о состоянии счета клиента!

Теперь обновленный csv содержит данные пользователя, тип транзакции, дату транзакции, сумму и номер транзакции. 

Важный момент.
Если BOOKING_TYP = I (внутренний, клиент переводит средства внутри банка) то транзакции необходимо сгруппировать по TRANSAKTION_NUMMER.
Все транзакции с BOOKING_TYP = I должны иметь ровно 2 записи. Одна из них это TRANSAKTION_TYPE = С, другая TRANSAKTION_TYPE = D.
Т.е. клиент совершил транзакцию и перевел с одного на другой счет определенную сумму.
