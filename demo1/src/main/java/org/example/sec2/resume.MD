# 🐒 Métodos de Creación de **Mono** en Project Reactor

El **Mono** en *Project Reactor* representa un flujo reactivo que puede emitir **cero o un elemento**.  
Estos métodos estáticos son fundamentales para **iniciar secuencias**.

---

## 💡 Generación de Elementos

Estos métodos crean un `Mono` a partir de un valor ya conocido o mediante una operación de obtención:

### `Mono.just(T data)`
Crea un `Mono` que **emite un único elemento** (`data`) y luego **completa**.
> Es el más simple y directo.

---

### `Mono.fromSupplier(Supplier<? extends T> supplier)`
Crea un `Mono` que ejecuta la función **Supplier** para obtener su valor **solo cuando es suscrito** (*evaluación perezosa*).

---

### `Mono.fromCallable(Callable<? extends T> callable)`
Similar a `fromSupplier`, pero usa un **Callable**,  
permitiendo el manejo de **excepciones verificadas** (*checked exceptions*).

---

## 🍃 Control de Estado de Flujo

Estos métodos se usan para crear `Mono` que representan la **finalización sin valor** o un **error inmediato**:

### `Mono.empty()`
Crea un `Mono` que termina inmediatamente **sin emitir ningún elemento** (`onComplete()` sin `onNext()`).  
Representa un resultado **exitoso pero vacío** (`Mono<Void>`).

---

### `Mono.error(Throwable error)`
Crea un `Mono` que **inmediatamente emite una señal de error** (`onError(error)`) sin emitir ningún valor.

---

## ⚙️ Integración con Estándares de Java

Estos métodos actúan como **puentes** para integrar código asíncrono o síncrono existente de Java:

### `Mono.fromRunnable(Runnable runnable)`
Crea un `Mono` que ejecuta el **Runnable** al ser suscrito,  
no emite ningún valor (siempre es `Mono<Void>`), y luego completa.
> Útil para efectos secundarios.

---

### `Mono.fromFuture(Future<? extends T> future)`
Convierte una **Future** de la concurrencia estándar de Java en un `Mono`.  
El `Mono` **esperará** a que el `Future` se complete y **emitirá su resultado**.

### `Mono.defer(Supplier<? extends Mono<? extends T>> supplier)`
Crea un `Mono` **nuevo para cada suscriptor**, usando el **Supplier** proporcionado.  
Es útil cuando quieres **evaluar o generar dinámicamente** el contenido del `Mono` cada vez que alguien se suscribe.
> Ideal para diferir la creación del flujo hasta el momento de la suscripción.

**Ejemplo:**
```java
Mono<String> mono = Mono.defer(() -> Mono.just(LocalDateTime.now().toString()));

---
