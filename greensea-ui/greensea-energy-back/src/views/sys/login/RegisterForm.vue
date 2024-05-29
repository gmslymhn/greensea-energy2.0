<template>
  <div v-if="getShow">
    <LoginFormTitle class="enter-x" />
    <Form class="p-4 enter-x" :model="formData" :rules="getFormRules" ref="formRef">
      <FormItem name="account" class="enter-x">
        <Input
          class="fix-auto-fill"
          size="large"
          v-model:value="formData.account"
          :placeholder="t('sys.login.userName')"
        />
      </FormItem>
      <FormItem name="nickName" class="enter-x">
        <Input
          class="fix-auto-fill"
          size="large"
          v-model:value="formData.nickName"
          :placeholder="t('请输入昵称')"
        />
      </FormItem>
      <FormItem name="mobile" class="enter-x">
        <Input
          size="large"
          v-model:value="formData.mobile"
          :placeholder="t('请输入邮箱')"
          class="fix-auto-fill"
        />
      </FormItem>
      <FormItem name="sms" class="enter-x">
        <CountdownInput
          size="large"
          class="fix-auto-fill"
          v-model:value="formData.sms"
          :placeholder="t('sys.login.smsCode')"
          @click="handleGetsmsCode"
        />
      </FormItem>
      <FormItem name="password" class="enter-x">
        <StrengthMeter
          size="large"
          v-model:value="formData.password"
          :placeholder="t('sys.login.password')"
        />
      </FormItem>
      <FormItem name="confirmPassword" class="enter-x">
        <InputPassword
          size="large"
          visibilityToggle
          v-model:value="formData.confirmPassword"
          :placeholder="t('sys.login.confirmPassword')"
        />
      </FormItem>

      <FormItem class="enter-x" name="policy">
        <!-- No logic, you need to deal with it yourself -->
        <Checkbox v-model:checked="formData.policy" size="small">
          {{ t('sys.login.policy') }}
        </Checkbox>
      </FormItem>
      <!-- 这个是注册按钮-->
      <Button
        type="primary"
        class="enter-x"
        size="large"
        block
        @click="handleRegister"
        :loading="loading"
      >
        {{ t('sys.login.registerButton') }}
      </Button>
      <!-- 这个是返回按钮-->
      <Button size="large" block class="mt-4 enter-x" @click="handleBackLogin">
        {{ t('sys.login.backSignIn') }}
      </Button>
    </Form>
  </div>
</template>
<script lang="ts" setup>
  import { reactive, ref, unref, computed } from 'vue';
  import LoginFormTitle from './LoginFormTitle.vue';
  import { Form, Input, Button, Checkbox, message } from 'ant-design-vue';
  import { StrengthMeter } from '@/components/StrengthMeter';
  import { CountdownInput } from '@/components/CountDown';
  import { useI18n } from '@/hooks/web/useI18n';
  import { useLoginState, useFormRules, useFormValid, LoginStateEnum } from './useLogin';
  import { SignApi, getSmsCode } from '@/api/sys/user';
  import {useRouter} from "vue-router";

  const FormItem = Form.Item;
  const InputPassword = Input.Password;
  const { t } = useI18n();
  const { handleBackLogin, getLoginState } = useLoginState();

  const formRef = ref();
  const loading = ref(false);
  const router = useRouter();

  const formData = reactive({
    account: '',
    nickName: '',
    password: '',
    confirmPassword: '',
    mobile: '',
    sms: '',
    policy: false,
  });
  const smsData = reactive({
    account: '',
    mobile: '',
  });

  const { getFormRules } = useFormRules(formData);
  const { validForm } = useFormValid(formRef);

  const getShow = computed(() => unref(getLoginState) === LoginStateEnum.REGISTER);

  async function handleRegister() {
    const data = await validForm();
    if (!data) return;
    console.log(data);
    try {
      const result = await SignApi({
        userAccount: data.account,
        userPassword: data.password,
        userNickname: data.nickName,
        userEmail: data.mobile,
        verificationCode: data.sms,
      });
      console.log(result);
      if (result.code === 200) {
        router.push('/login');
      }
    } catch (error) {
      console.log(error);
    }
  }

  async function handleGetsmsCode() {
    // 检查 account 和 mobile 字段是否填写
    if (!formData.account) {
      message.error('请填写账号');
      return;
    }
    if (!formData.mobile) {
      message.error('请填写手机号');
      return;
    }

    console.log('有这个点击动作嘛');
    try {
      const result = await getSmsCode({
        userAccount: formData.account,
        userEmail: formData.mobile,
      });
      console.log(result);
    } catch (e) {
      console.log(e);
    }
  }
</script>
