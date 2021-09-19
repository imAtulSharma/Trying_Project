# Wallet Algorithm

This is an algorithm to be made for use in a different android based projects.

## What the algorithm actually do
There are some type of coins to the user and we have to deduct an amount from the wallet (Which have different type of coins i.e. miniWallet).
First of all we deduct a specific amount from the mini wallets according to the percentages defined to satisfy the total amount need.
And if the amount is not satisfied from the given percentage then we can deduct amount from any type of wallet.
But we put a restriction to a particular wallet to deduct only the percentage described and not more than that.

NOTE: The core logic of algorithm is in calculateAmount function and others are for understanding purpose and for customisation.

## How to use this
You can simply change the percentage array and mini wallet array and can give the total amount to be deducted from the mini wallets.
You can also change the stationery wallet position from which only the specified percentage can be taken out means not more then that.

NOTE: All the amounts should be positive.

## Result of algorithm
Returns true if the amount need is satisfied otherwise false.

## Customisation
I have used color to understand properly that amount is deducted from which type of coin and how much amount to left to be deducted more.
