# Contributing

### Style Guide
* [Android Style Guide](https://github.com/ribot/android-guidelines/blob/master/project_and_code_guidelines.md)

### Git
* [GitFlow](http://nvie.com/posts/a-successful-git-branching-model/) 

### Git Commit Messages ###
- Use the present indicative in the messages
- Limit the first message line by up to 72 characters
- Use emoji at the beginning of each message. It help us to identify what's the purpose for each commit.


    | Code.                 | Emoji               | Description                                     |
    |-----------------------|---------------------|-------------------------------------------------|
    | `:art:`               | :art:               | when improving the format/structure of the code |
    | `:racehorse:`         | :racehorse:         | when improving performance                      |
    | `:memo:`              | :memo:              | when writing docs                               |
    | `:bug:`               | :bug:               | when fixing a bug                               |
    | `:fire:`              | :fire:              | when removing code or files                     |
    | `:green_heart:`       | :green_heart:       | when fixing CI build                            |
    | `:white_check_mark:`  | :white_check_mark:  | when adding tests                               |
    | `:lock:`              | :lock:              | when dealing with security                      |
    | `:arrow_up:`          | :arrow_up:          | when upgrading dependencies                     |
    | `:arrow_down:`        | :arrow_down:        | when downgrading dependencies                   |
    | `:shirt:`             | :shirt:             | when removing linter warnings                   |
    | `:bulb:`              | :bulb:              | new idea                                        |
    | `:construction:`      | :construction:      | work in progress                                |
    | `:heavy_plus_sign:`   | :heavy_plus_sign:   | when adding feature                             |
    | `:heavy_minus_sign:`  | :heavy_minus_sign:  | when removing feature                           |
    | `:facepunch:`         | :facepunch:         | when resolving conflicts                        |
    | `:hammer:`            | :hammer:            | when changing configuration files               |


Commit exemple:
```
git commit -m ":arrow_up: Update dependencies"
```

### Pull Requests ###

All Pull Request must be made to the `devolop branch`.

Before open a Pull Request, verify if all unit tests worked successfully, we use Bitrise to run tests, so if a test fails we'll receive a message and wouldn't accept the Pull Request.