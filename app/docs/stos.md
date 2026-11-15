# Analiza stosu aktywności (Back Stack)

Sekwencja: **Ekran 1 → Ekran 2 → Wstecz → Ekran 3 → Wstecz → Wstecz**

## Stos po każdym kroku

1. **Start (Ekran 1):** Tworzy się `MainActivity` (`onCreate`).
   ```text
   [ MainActivity ] ← widok

    Przejście na Ekran 2: OrderSummaryActivity ląduje na wierzchu (onCreate), MainActivity trafia w tło (onStop).
    Plaintext

    [ OrderSummaryActivity ] ← widok
    [ MainActivity         ]

    Wstecz: OrderSummaryActivity znika i jest niszczona (onStop, onDestroy). Wraca MainActivity.
    Plaintext

    [ MainActivity ] ← widok

    Przejście na Ekran 3: AboutActivity ląduje na wierzchu (onCreate), MainActivity trafia w tło (onStop).
    Plaintext

    [ AboutActivity ] ← widok
    [ MainActivity  ]

    Wstecz: AboutActivity znika i jest niszczona (onStop, onDestroy). Wraca MainActivity.
    Plaintext

    [ MainActivity ] ← widok

    Wstecz: MainActivity jest niszczona (onStop, onDestroy). Stos jest pusty – wyjście z aplikacji.
    Plaintext

    [ PUSTY STOS ]

Logcat (tag: STOS_TEST)
Plaintext

D/STOS_TEST: MainActivity -> onCreate
D/STOS_TEST: OrderSummaryActivity -> onCreate
D/STOS_TEST: MainActivity -> onStop
D/STOS_TEST: OrderSummaryActivity -> onStop
D/STOS_TEST: OrderSummaryActivity -> onDestroy
D/STOS_TEST: AboutActivity -> onCreate
D/STOS_TEST: MainActivity -> onStop
D/STOS_TEST: AboutActivity -> onStop
D/STOS_TEST: AboutActivity -> onDestroy
D/STOS_TEST: MainActivity -> onStop
D/STOS_TEST: MainActivity -> onDestroy