# GT-Ride-Sharing

## Requirement 

- Kotlin
- JDK-17

## Running with input files located in resource

input-1

```
ADD_DRIVER D1 1 1
ADD_DRIVER D2 4 5
ADD_DRIVER D3 2 2
ADD_RIDER R1 0 0
MATCH R1
START_RIDE RIDE-001 2 R1
STOP_RIDE RIDE-001 4 5 32
BILL RIDE-001
```

```
./gradlew run --args="input-1"
```

input-2

```
ADD_DRIVER D1 0 1
ADD_DRIVER D2 2 3
ADD_RIDER R1 3 5
ADD_DRIVER D3 4 2
ADD_RIDER R2 1 1
MATCH R1
MATCH R2
START_RIDE RIDE-101 1 R1
START_RIDE RIDE-102 1 R2
STOP_RIDE RIDE-101 10 2 48
STOP_RIDE RIDE-102 7 9 50
BILL RIDE-101
BILL RIDE-102
```

```
./gradlew run --args="input-2"
```

###
Problem link - https://www.geektrust.com/coding/detailed/ride-sharing
