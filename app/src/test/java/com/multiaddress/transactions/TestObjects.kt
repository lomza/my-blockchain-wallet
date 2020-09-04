package com.multiaddress.transactions

import com.multiaddress.transactions.data.*

const val anxPubKey: String = "xpub6CfLQa8fLgtouvLxrb8EtvjbXfoC1yqzH6YbTJw4dP7srt523AhcMV8Uh4K3TWSHz9oDWmn9MuJogzdGU3ncxkBsAC9wFBLmFrWT9Ek81kQ"

val aWallet = Wallet(
    finalBalance = 8549,
    totalReceived = 0,
    totalSent = 0
)

val anAddress = listOf(
    Address(
        address = anxPubKey,
        finalBalance = 8549,
        totalSent = 0,
        totalReceived = 0
    )
)

val transactionsList = listOf(
    Tx(
        hash = "85ec49c884f9ebce4a713032dbf73834ab756f79c2e02294450fe92f8a817d25",
        size = 817,
        weight = 3268,
        fee = 9816,
        result = -17791,
        balance = 0,
        time = 1542205791, // Wednesday, 14 November 2018 14:29:51
        inputs = listOf(
            Input(
                previousOut = Out(
                    xpub = Xpub(
                        m = "xpub6CfLQa8fLgtouvLxrb8EtvjbXfoC1yqzH6YbTJw4dP7srt523AhcMV8Uh4K3TWSHz9oDWmn9MuJogzdGU3ncxkBsAC9wFBLmFrWT9Ek81kQ"
                    ),
                    address = "1CvqiWoZc34ZZdm9c9Cr3L8EgVV3XRU3DT",
                    value = 694
                )
            )
        ),
        out = listOf(
            Out(
                xpub = Xpub(
                    m = "xpub6CfLQa8fLgtouvLxrb8EtvjbXfoC1yqzH6YbTJw4dP7srt523AhcMV8Uh4K3TWSHz9oDWmn9MuJogzdGU3ncxkBsAC9wFBLmFrWT9Ek81kQ"
                ),
                address = "1CvqiWoZc34ZZdm9c9Cr3L8EgVV3XRU3DT",
                value = 694
            ),
            Out(
                xpub = null,
                address = "1CfYnV12Do6F1faqE4yzHPWLKyxExrYjih",
                value = 7975
            )
        )
    ),
    Tx(
        hash = "cbc06203f949804a512290ade05dcab35cf30c16b43bb0ede6f5074f1f8c3b9e",
        size = 2843,
        weight = 11372,
        fee = 36881,
        result = -612687,
        balance = 0,
        time = 1547223190, // Friday, 11 January 2019 16:13:10
        inputs = listOf(
            Input(
                previousOut = Out(
                    xpub = Xpub(
                        m = "xpub6CfLQa8fLgtouvLxrb8EtvjbXfoC1yqzH6YbTJw4dP7srt523AhcMV8Uh4K3TWSHz9oDWmn9MuJogzdGU3ncxkBsAC9wFBLmFrWT9Ek81kQ"
                    ),
                    address = "16UfoQAGZPQYfMPrTzP4U8FBkTGk8xRPBH",
                    value = 3000
                )
            )
        ),
        out = listOf(
            Out(
                xpub = null,
                address = "1LMmvgrvSpufWEQFj4mrRYGuA7L44kvg8w",
                value = 575806
            )
        )
    ),
    Tx(
        hash = "c83e338f8fd064eee481eb346694a4dc8b5c2afd1117ce8321f75b394c746668",
        size = 225,
        weight = 900,
        fee = 678,
        result = 15389,
        balance = 0,
        time = 1540207704, // Monday, 22 October 2018 11:28:24
        inputs = listOf(
            Input(
                previousOut = Out(
                    xpub = null,
                    address = "1APJ5qiJUqX65zMvixFuLE9mjN4Nyq75Ug",
                    value = 116149
                )
            )
        ),
        out = listOf(
            Out(
                xpub = Xpub(
                    m = "xpub6CfLQa8fLgtouvLxrb8EtvjbXfoC1yqzH6YbTJw4dP7srt523AhcMV8Uh4K3TWSHz9oDWmn9MuJogzdGU3ncxkBsAC9wFBLmFrWT9Ek81kQ"
                ),
                address = "18XfyNe4FHPw8RZLpi7KK27pyKwVZo9og2",
                value = 15389
            )
        )
    ),
    Tx(
        hash = "316aea60c8a8d006eb711e306cc8a4781822543ef9c417410768e4f2b9d5ecfb",
        size = 374,
        weight = 1496,
        fee = 4114,
        result = -11859,
        balance = 0,
        time = 1541532000, // Tuesday, 6 November 2018 19:20:00
        inputs = listOf(
            Input(
                previousOut = Out(
                    xpub = Xpub(
                        m = "xpub6CfLQa8fLgtouvLxrb8EtvjbXfoC1yqzH6YbTJw4dP7srt523AhcMV8Uh4K3TWSHz9oDWmn9MuJogzdGU3ncxkBsAC9wFBLmFrWT9Ek81kQ"
                    ),
                    address = "1CvqiWoZc34ZZdm9c9Cr3L8EgVV3XRU3DT",
                    value = 694
                )
            ),
            Input(
                previousOut = Out(
                    xpub = Xpub(
                        m = "xpub6CfLQa8fLgtouvLxrb8EtvjbXfoC1yqzH6YbTJw4dP7srt523AhcMV8Uh4K3TWSHz9oDWmn9MuJogzdGU3ncxkBsAC9wFBLmFrWT9Ek81kQ"
                    ),
                    address = "1CvqiWoZc34ZZdm9c9Cr3L8EgVV3XRU3DT",
                    value = 694
                )
            )
        ),
        out = listOf(
            Out(
                xpub = Xpub(
                    m = "xpub6CfLQa8fLgtouvLxrb8EtvjbXfoC1yqzH6YbTJw4dP7srt523AhcMV8Uh4K3TWSHz9oDWmn9MuJogzdGU3ncxkBsAC9wFBLmFrWT9Ek81kQ"
                ),
                address = "12tAMXR6EfpQonjrfCuftktUedEQsPnkJf",
                value = 6782
            ),
            Out(
                xpub = Xpub(
                    m = "xpub6CfLQa8fLgtouvLxrb8EtvjbXfoC1yqzH6YbTJw4dP7srt523AhcMV8Uh4K3TWSHz9oDWmn9MuJogzdGU3ncxkBsAC9wFBLmFrWT9Ek81kQ"
                ),
                address = "1HRP4HW2qjumc8WSgKXzCN6Kv41XwjVKeE",
                value = 9186
            )
        )
    )
)
