package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс отображает набор доступных операций.
 * @author Alexander Belov (whiterabbit.nsk@gmail.com)
 * @since 20.06.18
 */
public class Tracker {
    /**
     * база данных счетов с привязкой к пользователям.
     */
    Map<User, List<Account>> bank = new HashMap<>();
    /**
     *
     */
    private Map<String, Account> requisitesData = new HashMap<>();
    /**
     * Добавление полбзователя.
     * @param user пользователь
     */
    public final void addUser(final User user) {
        this.bank.putIfAbsent(user, user.getAccounts());
    }

    /**
     * Удаление пользователя.
     * @param user пользователь
     */
    public final void deleteUser(final User user) {
        this.bank.remove(user);
    }

    /**
     * Открытие нового счета
     * @param passport id пользователя
     * @param account счет
     */
    public final void addAccountToUser(final String passport, final Account account) {
        for (User user : this.bank.keySet()) {
            if (user.getPassport().equals(passport)) {
                user.addAccount(account);
                requisitesData.put(account.getRequisites(), account);
                break;
            }
        }
    }

    /**
     * Удаление счета.
     * @param passport id пользователя
     * @param account счет
     */
    public final void deleteAccountFromUser(final String passport, final Account account) {
        for (User user : this.bank.keySet()) {
            if (user.getPassport().equals(passport)) {
                requisitesData.remove(account.getRequisites());
                user.deleteAccount(account);
                break;
            }
        }
    }

    /**
     * Все счета пользователя.
     * @param passport id пользователя
     * @return список счетов
     */
    public final List<Account> getUserAccounts(final String passport) {
        List<Account> result = null;
        for (User user : this.bank.keySet()) {
            if (user.getPassport().equals(passport)) {
                result = user.getAccounts();
                break;
            }
        }
        return result;
    }

    /**
     * Перевод средств.
     * @param srcPassport id отправителя
     * @param srcRequisite реквизиты счета отправителя
     * @param dstPassport id получателя
     * @param dstRequisite реквизиты счета получателя
     * @param amount средства
     * @return успех перевода
     */
    public final boolean transferMoney(final String srcPassport, final String srcRequisite,
                                       final String dstPassport, final String dstRequisite,
                                       final double amount) {
        boolean result = false;
        User sender = null;
        User recipient = null;
        for (User user : bank.keySet()) {
            if (user.getPassport().equals(srcPassport)) {
                sender = user;
            } else if (user.getPassport().equals(dstPassport)) {
                recipient = user;
            } else if (sender != null && recipient != null) {
                break;
            }
        }
        Account src = searchAccount(srcRequisite);
        Account dst = searchAccount(dstRequisite);
        if (src != null && dst != null && src.getValue() >= amount) {
            src.changeValue(-amount);
            dst.changeValue(amount);
            result = true;
            System.out.println("Transfer succeed!");
        } else {
            System.out.println("Transfer failed!");
        }
        return result;
    }
    public Account searchAccount(String requisite) {
        return requisitesData.get(requisite);
    }
}
