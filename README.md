<a href="https://discord.gg/xTsGHSx42W">
    <p align="center">
        <img width="500" height="250" src="https://cdn.discordapp.com/attachments/837072013360365599/1059585591030849536/slaxnetwork2.png" alt=""/>
    </p>
</a>

<p align="center">
    <strong>Kyouko, a backend built with Ktor.</strong>
</p>

---
# Kyouko
Kyouko is the backend service holding Slax Network together.

The primary goal of implementing this backend was to consolidate persistent data and handle communication efficiently and
allow developers to implement games onto Slax Network without having to worry about writing their own messy backend code
to integrate with our system.

If you want to contribute to our project read our [Contributing Guidelines](https://github.com/SlaxNetwork/kyouko/blob/main/CONTRIBUTING.md) beforehand.

# Technologies
Kyouko, uses [Ktor](https://ktor.io/) to handle routing and requests to our backend service, we use [MongoDB](https://www.mongodb.com/)
to manage persistent data and [KMongo](https://litote.org/kmongo/) to interface with that persisted data in our service.